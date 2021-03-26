package com.gsm.chwijuntime.controller.release;

import com.gsm.chwijuntime.dto.MemberJoinDto;
import com.gsm.chwijuntime.dto.MemberLoginDto;
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

    @PostMapping("/join")
    public CommonResult join(@RequestBody MemberJoinDto memberJoinDto) throws IllegalAccessException {
        memberService.InsertMember(memberJoinDto);
        return responseService.getSuccessResult();
    }

    @PostMapping("/login")
    public SingleResult<Map<String, String>> login(@RequestBody MemberLoginDto memberLoginDto) throws Exception {
        Map<String ,String> map = new HashMap<>();
        Member member = memberService.findMember(memberLoginDto);

        String accessToken = jwtTokenProvider.generateToken(member);
        String refreshToken = jwtTokenProvider.generateRefreshToken(member);
        Iterator<? extends GrantedAuthority> authorityIterator = member.getAuthorities().iterator();
        String roles = authorityIterator.next().toString();
        redisUtil.setDataExpire(member.getUsername(), refreshToken, jwtTokenProvider.REFRESH_TOKEN_VALIDATION_SECOND);

        map.put("userEmail", member.getMemberEmail());
        map.put("userClassNumber", member.getMemberClassNumber());
        map.put("roles", roles);
        map.put("accessToken", accessToken);
        return responseService.getSingleResult(map);
    }

    @PostMapping("/logout")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    public CommonResult logout() {
        memberService.logoutMember();
        return responseService.getSuccessResult();
    }
}
