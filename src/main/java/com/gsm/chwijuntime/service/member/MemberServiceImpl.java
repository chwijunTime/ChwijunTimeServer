package com.gsm.chwijuntime.service.member;

import com.gsm.chwijuntime.advice.exception.*;
import com.gsm.chwijuntime.aop.memorycheck.MemoryCheck;
import com.gsm.chwijuntime.aop.timecheck.TimeCheck;
import com.gsm.chwijuntime.dto.member.*;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.model.tagmapping.MemberTag;
import com.gsm.chwijuntime.repository.MemberRepository;
import com.gsm.chwijuntime.repository.TagRepository;
import com.gsm.chwijuntime.repository.tag.MemberTagRepository;
import com.gsm.chwijuntime.util.GetUserEmailUtil;
import com.gsm.chwijuntime.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final RedisUtil redisUtil;
    private final MemberRepository memberRepository;
    private final MemberTagRepository memberTagRepository;
    private final TagRepository tagRepository;
    private final PasswordEncoder passwordEncoder;
    private final GetUserEmailUtil getUserEmailUtil;

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

    @MemoryCheck @TimeCheck
    @Override
    public Member findMember(MemberLoginDto memberLoginDto) {
        Member member = memberRepository.findByMemberEmail(memberLoginDto.getMemberEmail()).orElseThrow(EmailNotFoundException::new);
        boolean check = passwordEncoder.matches(memberLoginDto.getMemberPassword(), member.getMemberPassword());
        if(!check) {
            throw new IncorrectPasswordException();
        }
        return member;
    }

    @Override
    public void logoutMember() {
        String userEmail = getUserEmailUtil.getUserEmail();
        redisUtil.deleteData(userEmail);
    }

    @Override
    public Member UserInfo() {
        String UserEmail = getUserEmailUtil.getUserEmail();
        return memberRepository.findByMemberEmail(UserEmail).orElseThrow(CAuthenticationEntryPointException::new);
    }

    @Transactional
    @Override
    public void memberProfileSave(MemberProfileSaveDto memberProfileSaveDto) {
        for (String i : memberProfileSaveDto.getTagName()) {
            Tag tag = tagRepository.findByTagName(i);
            if(tag == null) {
                throw new NotFoundTagException();
            }
            String userEmail = getUserEmailUtil.getUserEmail();
            Member member = memberRepository.findByMemberEmail(userEmail).orElseThrow(CAuthenticationEntryPointException::new);
            memberProfileSaveDto.MappingTag_Member(tag, member);
            //프로필 업데이트
            member.change_profile(memberProfileSaveDto.getMemberPhoneNumber(), memberProfileSaveDto.getMemberETC());
            memberTagRepository.save(memberProfileSaveDto.ToEntityByMemberTag());
        }
    }

    @Transactional
    @Override
    public void updateMemberProfile(UpdateMemberProfileDto updateMemberProfileDto) {
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        member.change_profile(updateMemberProfileDto.getMemberPhoneNumber(), updateMemberProfileDto.getMemberETC());
        List<MemberTag> findMemberTag = memberTagRepository.findByMember(member);
        // 관련된 태그들 모두 삭제
        for (MemberTag memberTag : findMemberTag) {
            memberTagRepository.delete(memberTag);
        }
        //태그 저장
        for (String i : updateMemberProfileDto.getTagName()){
            Tag tag = tagRepository.findByTagName(i);
            if(tag == null) {
                throw new NotFoundTagException();
            }
            updateMemberProfileDto.MappingTag_Member(tag, member);
            memberTagRepository.save(updateMemberProfileDto.ToEntityByMemberTag());
        }
    }

    @Override
    public void userEmailCheck(String email) {
        Optional<Member> member = memberRepository.findByMemberEmail(email);
        if(!member.isEmpty()){
            throw new UserDuplicationException();
        }
    }

    @Transactional
    @Override
    public void change_password(MemberPasswordChangeDto memberPasswordChangeDto) {
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        String pw = passwordEncoder.encode(memberPasswordChangeDto.getMemberPassword());
        member.change_password(pw);
    }

    @Transactional
    @Override
    public MemberTagResDto viewMember() {
        List<String> tags = new ArrayList<>();
        String userEmail = getUserEmailUtil.getUserEmail();
        Member findMember = memberRepository.findByMemberEmail(userEmail).orElseThrow(CAuthenticationEntryPointException::new);
        List<MemberTag> findMemberTag = memberTagRepository.findByMember(findMember);
        for (MemberTag memberTag : findMemberTag) {
            String Name = memberTag.getTag().getTagName();
            Tag tag = tagRepository.findByTagName(Name);
            tags.add(tag.getTagName());
        }
        MemberTagResDto memberTagResDto = MemberTagResDto.mapping(findMember, tags);
        return memberTagResDto;
    }
}