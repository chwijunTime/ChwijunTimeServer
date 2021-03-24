package com.gsm.chwijuntime.service;

import com.gsm.chwijuntime.dto.MemberJoinDTO;
import com.gsm.chwijuntime.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Long InsertMember(MemberJoinDTO memberJoinDTO) {
        Long MemberIdx = memberRepository.save(memberJoinDTO.ToEntity()).getMemberIdx();
        return MemberIdx;
    }

    
}
