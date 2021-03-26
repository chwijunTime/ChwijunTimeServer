package com.gsm.chwijuntime.service;

import com.gsm.chwijuntime.dto.MemberJoinDto;
import com.gsm.chwijuntime.dto.MemberLoginDto;
import com.gsm.chwijuntime.model.Member;

public interface MemberService {

    void InsertMember(MemberJoinDto memberJoinDto);
    Member findMember(MemberLoginDto memberLoginDto);
    void logoutMember();
}
