package com.gsm.chwijuntime.service.member;

import com.gsm.chwijuntime.dto.member.*;
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
    void updateMemberProfile(UpdateMemberProfileDto updateMemberProfileDto);

    // 이메일 중복
    void userEmailCheck(String email);

    //비밀번호 변경
    void change_password(MemberPasswordChangeDto memberPasswordChangeDto);
}
