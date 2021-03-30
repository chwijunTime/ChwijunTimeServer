package com.gsm.chwijuntime.service.member;

import com.gsm.chwijuntime.dto.member.MemberJoinDto;
import com.gsm.chwijuntime.dto.member.MemberLoginDto;
import com.gsm.chwijuntime.dto.member.MemberProfileSaveDto;
import com.gsm.chwijuntime.dto.member.MemberTagResDto;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.tagmapping.MemberTag;

import javax.servlet.http.HttpServletRequest;

public interface MemberService {

    void InsertMember(MemberJoinDto memberJoinDto);
    Member findMember(MemberLoginDto memberLoginDto);
    void logoutMember();
    Member UserInfo();
    void memberProfileSave(MemberProfileSaveDto memberProfileSaveDto);
    void updateMemberProfile(MemberProfileSaveDto memberProfileSaveDto);
    MemberTagResDto viewMember();

}
