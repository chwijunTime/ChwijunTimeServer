package com.gsm.chwijuntime.service;

import com.gsm.chwijuntime.dto.MemberJoinDto;
import com.gsm.chwijuntime.dto.MemberLoginDto;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.repository.MemberRepository;
import com.gsm.chwijuntime.util.JwtTokenProvider;
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
public class MemberService {

    private final RedisUtil redisUtil;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void InsertMember(MemberJoinDto memberJoinDto) throws IllegalAccessException {
        Optional<Member> member = memberRepository.findByMemberEmail(memberJoinDto.getMemberEmail());
        if(member.isEmpty()) {
            String password = memberJoinDto.getMemberPassword();
            memberJoinDto.setMemberPassword(passwordEncoder.encode(password));
            memberRepository.save(memberJoinDto.ToEntity()).getMemberIdx();
        } else {
            throw new IllegalAccessException("이메일 중복");
        }
    }


    public String findMember(MemberLoginDto memberLoginDto) throws Exception {
        Member member = memberRepository.findByMemberEmail(memberLoginDto.getMemberEmail()).orElseThrow(null);
        boolean check = passwordEncoder.matches(memberLoginDto.getMemberPasword(), member.getMemberPassword());
        if(!check) {
            throw new Exception("비밀번호가 틀렸습니다.");
        }
        String accessToken = jwtTokenProvider.generateToken(member);
        String refreshToken = jwtTokenProvider.generateRefreshToken(member);
        redisUtil.setDataExpire(member.getUsername(), refreshToken, jwtTokenProvider.REFRESH_TOKEN_VALIDATION_SECOND);
        return accessToken;
    }

    public void logoutMember() {
        String userEmail = GetUserEmail();
        redisUtil.deleteData(userEmail);
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
