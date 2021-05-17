package com.gsm.chwijuntime.service.email;

import com.gsm.chwijuntime.advice.exception.CAuthenticationEntryPointException;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MemberRepository memberRepository;

    public boolean userEmailCheck(String userEmail, String classNumber) {
        Optional<Member> byMemberEmailAndMemberClassNumber = memberRepository.findByMemberEmailAndMemberClassNumber(userEmail, classNumber);
        if(byMemberEmailAndMemberClassNumber.isEmpty()){
            System.out.println("유저 정보가 없습니다.");
            return false;
        } else {
            return true;
        }
    }

}
