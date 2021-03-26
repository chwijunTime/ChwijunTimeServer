package com.gsm.chwijuntime.service;

import com.gsm.chwijuntime.dto.MemberJoinDTO;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public void InsertMember(MemberJoinDTO memberJoinDTO) throws IllegalAccessException {
        Optional<Member> member = memberRepository.findByMemberEmail(memberJoinDTO.getMemberEmail());
        if(member.isEmpty()) {
            memberRepository.save(memberJoinDTO.ToEntity()).getMemberIdx();
        } else {
            throw new IllegalAccessException("이메일 중복");
        }
    }


}
