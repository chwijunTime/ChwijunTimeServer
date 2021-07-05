package com.gsm.chwijuntime.controller.release;

import com.gsm.chwijuntime.dto.email.MailDto;
import com.gsm.chwijuntime.dto.member.*;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.response.CommonResult;
import com.gsm.chwijuntime.model.response.ResponseService;
import com.gsm.chwijuntime.model.response.SingleResult;
import com.gsm.chwijuntime.security.JwtTokenProvider;
import com.gsm.chwijuntime.service.email.SendEmailService;
import com.gsm.chwijuntime.service.email.UserService;
import com.gsm.chwijuntime.service.member.MemberService;
import com.gsm.chwijuntime.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Api(tags = {"1. 회원"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
@CrossOrigin("http://localhost:3000")
@Slf4j
public class MemberController {

    private final SendEmailService sendEmailService;
    private final MemberService memberService;
    private final ResponseService responseService;
    private final RedisUtil redisUtil;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @ApiOperation(value = "가입", notes = "회원가입을 한다.")
    @ResponseBody
    @PostMapping("/join")
    public CommonResult join(@Valid @RequestBody MemberJoinDto memberJoinDto) throws IllegalAccessException {
        memberService.InsertMember(memberJoinDto);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "아이디 중복 체크", notes = "회원가입을 할때 이메일 중복 체크를 한다.")
    @ResponseBody
    @PostMapping("/email-check")
    public CommonResult userEmailCheck(@Valid @RequestBody IdDuplicateCheckDto idDuplicateCheckDto) {
        memberService.userEmailCheck(idDuplicateCheckDto.getEmail());
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @ResponseBody
    @PostMapping("/login")
    public SingleResult<MemberLoginResDto> login(@Valid @RequestBody MemberLoginDto memberLoginDto) throws Exception {
        MemberLoginResDto member = memberService.findMember(memberLoginDto);
        return responseService.getSingleResult(member);
    }

    @ApiOperation(value = "로그아웃", notes = "사용자가 로그아웃한다.")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @PostMapping("/logout")
    public CommonResult logout() {
        memberService.logoutMember();
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "유저 정보", notes = "현재 로그인 된 유저 정보를 가져온다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @GetMapping("/userinfo")
    public SingleResult<Member> userinfo() {
        Member member = memberService.UserInfo();
        return responseService.getSingleResult(member);
    }

    @ApiOperation(value = "프로필 생성", notes = "유저가 프로필을 설정한다.")

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @PostMapping("/profile")
    public CommonResult profile(@Valid @RequestBody MemberProfileSaveDto memberProfileSaveDto) {
        memberService.memberProfileSave(memberProfileSaveDto);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "프로필 보기", notes = "유저가 프로필을 확인한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @GetMapping("/view-profile")
    public SingleResult<MemberTagResDto> view_profile() {
        MemberTagResDto viewMember = memberService.viewMember();
        return responseService.getSingleResult(viewMember);
    }

    @ApiOperation(value = "프로필 수정", notes = "유저가 프로필을 수정한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @PutMapping("/update-profile")
    public CommonResult update_profile(@RequestBody UpdateMemberProfileDto updateMemberProfileDto) {
        memberService.updateMemberProfile(updateMemberProfileDto);
        return responseService.getSuccessResult();
    }

    @ResponseBody
    @ApiOperation(value = "비밀번호를 변경하기 위한 이메일과 학번 체크", notes = "유저가 비밀번호를 변경하기위 해서 권한을 체크한다.")
    @GetMapping("/check/findPw")
    public SingleResult<Map<String, Boolean>> pw_find(@RequestParam String userEmail, @RequestParam String classNumber){
        Map<String,Boolean> json = new HashMap<>();
        boolean pwFindCheck = userService.userEmailCheck(userEmail, classNumber);
        json.put("check", pwFindCheck);
        return responseService.getSingleResult(json);
    }

    @ResponseBody
    @ApiOperation(value = "등록된 이메일로 임시비밀번호를 발송하고 발송된 임시비밀번호로 사용자의 pw를 변경", notes = "유저가 임시 비밀번호를 지급 받는다.")
    @PostMapping("/check/findPw/sendEmail")
    public CommonResult sendEmail(@RequestParam String userEmail){
        MailDto dto = sendEmailService.createMailAndChangePassword(userEmail);
        sendEmailService.mailSend(dto);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "비밀번호 변경하기", notes = "유저가 비밀번호를 변경한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @PutMapping("/password-change")
    public CommonResult passwordChange(@Valid @RequestBody MemberPasswordChangeDto memberPasswordChangeDto) {
        memberService.change_password(memberPasswordChangeDto);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "새로운 토큰 요청하기", notes = "유저가 비밀번호를 변경한다.")
    @ResponseBody
    @PostMapping("/auth/refresh")
    public SingleResult<Map<String, String>> AuthRefresh(@Valid @RequestBody AuthRefreshDto authRefreshDto, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String newToken = memberService.authRefresh(authRefreshDto, httpServletRequest, httpServletResponse);
        httpServletResponse.addHeader("newToken", newToken);
        Map<String, String> map = new HashMap<>();
        map.put("newToken", newToken);
        return responseService.getSingleResult(map);
    }
}