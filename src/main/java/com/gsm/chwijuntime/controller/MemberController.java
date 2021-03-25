package com.gsm.chwijuntime.controller;

import com.gsm.chwijuntime.dto.MemberJoinDTO;
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

    @PostMapping("/join")
    public void join(@RequestBody MemberJoinDTO memberJoinDTO) throws IllegalAccessException {
        memberService.InsertMember(memberJoinDTO);
    }


}
