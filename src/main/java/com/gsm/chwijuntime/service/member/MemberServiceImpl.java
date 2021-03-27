package com.gsm.chwijuntime.service.member;

import com.gsm.chwijuntime.advice.exception.EmailNotFoundException;
import com.gsm.chwijuntime.advice.exception.IncorrectPasswordException;
import com.gsm.chwijuntime.advice.exception.UserDuplicationException;
import com.gsm.chwijuntime.dto.member.MemberJoinDto;
import com.gsm.chwijuntime.dto.member.MemberLoginDto;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.repository.MemberRepository;
import com.gsm.chwijuntime.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final RedisUtil redisUtil;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void InsertMember(MemberJoinDto memberJoinDto) {
        Optional<Member> member = memberRepository.findByMemberEmail(memberJoinDto.getMemberEmail());
        if(member.isEmpty()) {
            String password = memberJoinDto.getMemberPassword();
            memberJoinDto.setMemberPassword(passwordEncoder.encode(password));
            memberRepository.save(memberJoinDto.ToEntity()).getMemberIdx();
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
