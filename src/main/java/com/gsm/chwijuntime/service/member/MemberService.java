package com.gsm.chwijuntime.service.member;

import com.gsm.chwijuntime.dto.member.MemberJoinDto;
import com.gsm.chwijuntime.dto.member.MemberLoginDto;
import com.gsm.chwijuntime.dto.member.MemberProfileSaveDto;
import com.gsm.chwijuntime.dto.member.MemberTagResDto;
import com.gsm.chwijuntime.model.Member;

public interface MemberService {
    // 회원가입
    void InsertMember(MemberJoinDto memberJoinDto);

    // 로그인
    Member findMember(MemberLoginDto memberLoginDto);

    // 로그아웃
    void logoutMember();

    // 현재 로그인된 사용자 정보 보기
    Member UserInfo();

    // 프로필 저장
    void memberProfileSave(MemberProfileSaveDto memberProfileSaveDto);

    // 사용자 정보 조회
    MemberTagResDto viewMember();

    // 프로필 업데이트
    void updateMemberProfile(MemberProfileSaveDto memberProfileSaveDto);

    // 이메일 중복
    void userEmailCheck(String email);
}
