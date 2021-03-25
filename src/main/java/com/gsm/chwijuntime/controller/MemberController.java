package com.gsm.chwijuntime.controller;

import com.gsm.chwijuntime.dto.MemberJoinDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @PostMapping("/join")
    public void join(@RequestBody MemberJoinDTO memberJoinDTO){
        System.out.println("memberJoinDTO = " + memberJoinDTO.getMemberEmail());
    }


}
