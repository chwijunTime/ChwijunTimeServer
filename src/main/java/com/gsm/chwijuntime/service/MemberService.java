package com.gsm.chwijuntime.service;

import com.gsm.chwijuntime.dto.MemberJoinDTO;
import com.gsm.chwijuntime.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public void InsertMember(MemberJoinDTO memberJoinDTO) {
        memberRepository.save(memberJoinDTO.ToEntity());
    }

    
}
