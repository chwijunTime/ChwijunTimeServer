package com.gsm.chwijuntime.service.member;

import com.gsm.chwijuntime.advice.exception.EmailNotFoundException;
import com.gsm.chwijuntime.advice.exception.IncorrectPasswordException;
import com.gsm.chwijuntime.advice.exception.UserDuplicationException;
import com.gsm.chwijuntime.dto.member.MemberJoinDto;
import com.gsm.chwijuntime.dto.member.MemberLoginDto;
import com.gsm.chwijuntime.dto.member.MemberProfileSaveDto;
import com.gsm.chwijuntime.dto.member.MemberTagResDto;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.model.tagmapping.MemberTag;
import com.gsm.chwijuntime.repository.MemberRepository;
import com.gsm.chwijuntime.repository.TagRepository;
import com.gsm.chwijuntime.repository.tag.MemberTagRepository;
import com.gsm.chwijuntime.util.JwtTokenProvider;
import com.gsm.chwijuntime.util.RedisUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final RedisUtil redisUtil;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final MemberTagRepository memberTagRepository;
    private final TagRepository tagRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void InsertMember(MemberJoinDto memberJoinDto) {
        Optional<Member> member = memberRepository.findByMemberEmail(memberJoinDto.getMemberEmail());
        if(member.isEmpty()) {
            String password = memberJoinDto.getMemberPassword();
            memberJoinDto.setMemberPassword(passwordEncoder.encode(password));
            memberRepository.save(memberJoinDto.ToEntityByMember()).getMemberIdx();
        } else {
            throw new UserDuplicationException();
        }
    }

    @Override
    public Member findMember(MemberLoginDto memberLoginDto) {
        Member member = memberRepository.findByMemberEmail(memberLoginDto.getMemberEmail()).orElseThrow(EmailNotFoundException::new);
        boolean check = passwordEncoder.matches(memberLoginDto.getMemberPasword(), member.getMemberPassword());
        if(!check) {
            throw new IncorrectPasswordException();
        }
        return member;
    }

    @Override
    public void logoutMember() {
        String userEmail = GetUserEmail();
        redisUtil.deleteData(userEmail);
    }

    @Override
    public Member UserInfo() {
        String UserEmail = GetUserEmail();
        return memberRepository.findByMemberEmail(UserEmail).orElseThrow(null);
    }

    @Transactional
    @Override
    public void memberProfileSave(MemberProfileSaveDto memberProfileSaveDto) {
        for (String i : memberProfileSaveDto.getTagName()) {
            Tag tag = tagRepository.findByTagName(i);
            String userEmail = GetUserEmail();
            Member member = memberRepository.findByMemberEmail(userEmail).orElseThrow(null);
            memberProfileSaveDto.MappingTag_Member(tag, member);
            //프로필 업데이트
            member.Change_profile(memberProfileSaveDto.getMemberPhoneNumber(), memberProfileSaveDto.getMemberETC());
            memberTagRepository.save(memberProfileSaveDto.ToEntityByMemberTag());
        }
    }

    @Transactional
    @Override
    public void updateMemberProfile(MemberProfileSaveDto memberProfileSaveDto) {
        //나중에 생각
    }

    @Transactional
    @Override
    public MemberTagResDto viewMember() {
        List<Tag> tags = new ArrayList<>();
        Member findMember = memberRepository.findByMemberEmail(GetUserEmail()).orElseThrow(null);
        List<MemberTag> findMemberTag = memberTagRepository.findByMember(findMember);
        for (MemberTag memberTag : findMemberTag) {
            String Name = memberTag.getTag().getTagName();
            System.out.println("Name = " + Name);

            Tag tag = tagRepository.findByTagName(Name);
            tags.add(tag);

        }
        MemberTagResDto memberTagResDto = MemberTagResDto.mapping(findMember, tags);
        return memberTagResDto;
    }

    //현재 사용자의 ID를 Return
    public String GetUserEmail() {
        String userEmail;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            userEmail = ((UserDetails)principal).getUsername();
        } else {
            userEmail = principal.toString();
        }
        return userEmail;
    }
}