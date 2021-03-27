package com.gsm.chwijuntime.controller.release;

import com.gsm.chwijuntime.dto.MemberJoinDto;
import com.gsm.chwijuntime.dto.MemberLoginDto;
import com.gsm.chwijuntime.dto.MemberLoginResDto;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.response.CommonResult;
import com.gsm.chwijuntime.model.response.ResponseService;
import com.gsm.chwijuntime.model.response.SingleResult;
import com.gsm.chwijuntime.service.MemberService;
import com.gsm.chwijuntime.util.JwtTokenProvider;
import com.gsm.chwijuntime.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Api(tags = {"1. 회원"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
@CrossOrigin("http://localhost:3000")
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final ResponseService responseService;
    private final RedisUtil redisUtil;
    private final JwtTokenProvider jwtTokenProvider;

    @ApiOperation(value = "가입", notes = "회원가입을 한다.")
    @PostMapping("/join")
    public CommonResult join(@RequestBody MemberJoinDto memberJoinDto) throws IllegalAccessException {
        memberService.InsertMember(memberJoinDto);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @PostMapping("/login")
    public SingleResult<MemberLoginResDto> login(@RequestBody MemberLoginDto memberLoginDto) throws Exception {
        Member member = memberService.findMember(memberLoginDto);
        String accessToken = jwtTokenProvider.generateToken(member);
        String refreshToken = jwtTokenProvider.generateRefreshToken(member);
        String roles = member.String_Role(member);
        redisUtil.setDataExpire(member.getUsername(), refreshToken, jwtTokenProvider.REFRESH_TOKEN_VALIDATION_SECOND);
        MemberLoginResDto memberLoginResDto = MemberLoginResDto.mapping(member.getMemberEmail(), member.getMemberClassNumber(), roles, accessToken);
        return responseService.getSingleResult(memberLoginResDto);
    }

    @ApiOperation(value = "로그아웃", notes = "사용자가 로그아웃한다.")
    @PostMapping("/logout")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    public CommonResult logout() {
        memberService.logoutMember();
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "유저 정보", notes = "현재 로그인 된 유저 정보를 가져온다.")
    @PostMapping("/userinfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    public SingleResult<Member> userinfo(){
        Member member = memberService.UserInfo();
        return responseService.getSingleResult(member);
    }
}
