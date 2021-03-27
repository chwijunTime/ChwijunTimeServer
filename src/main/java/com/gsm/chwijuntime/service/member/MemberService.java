package com.gsm.chwijuntime.service.member;

import com.gsm.chwijuntime.dto.member.MemberJoinDto;
import com.gsm.chwijuntime.dto.member.MemberLoginDto;
import com.gsm.chwijuntime.model.Member;

public interface MemberService {

    void InsertMember(MemberJoinDto memberJoinDto);
    Member findMember(MemberLoginDto memberLoginDto);
    void logoutMember();
    Member UserInfo();
}
