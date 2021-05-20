package com.gsm.chwijuntime.service.member;

import com.gsm.chwijuntime.dto.member.*;
import com.gsm.chwijuntime.model.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface MemberService {
    // 회원가입
    void InsertMember(MemberJoinDto memberJoinDto);

    // 로그인
    MemberLoginResDto findMember(MemberLoginDto memberLoginDto);

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

    // 새로운 AccessToken 발급
    String authRefresh(AuthRefreshDto authRefreshDto, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}
