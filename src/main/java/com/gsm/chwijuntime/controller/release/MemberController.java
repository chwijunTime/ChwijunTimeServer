package com.gsm.chwijuntime.controller.release;

import com.gsm.chwijuntime.dto.MemberJoinDto;
import com.gsm.chwijuntime.dto.MemberLoginDto;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.response.CommonResult;
import com.gsm.chwijuntime.model.response.ResponseService;
import com.gsm.chwijuntime.model.response.SingleResult;
import com.gsm.chwijuntime.service.MemberService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"1. 회원"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
@CrossOrigin("http://localhost:3000")
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final ResponseService responseService;

    @PostMapping("/join")
    public CommonResult join(@RequestBody MemberJoinDto memberJoinDto) throws IllegalAccessException {
        memberService.InsertMember(memberJoinDto);
        return responseService.getSuccessResult();
    }

    @PostMapping("/login")
    public SingleResult<Member> login(@RequestBody MemberLoginDto memberLoginDto) throws Exception {
        Member member = memberService.findMember(memberLoginDto);
        return responseService.getSingleResult(member);
    }

    @PostMapping("/logout")
    public CommonResult logout() {
        memberService.logoutMember();
        return responseService.getSuccessResult();
    }
}
