package com.gsm.chwijuntime.service;

import com.gsm.chwijuntime.dto.member.MemberJoinDto;
import com.gsm.chwijuntime.dto.member.MemberLoginDto;
import com.gsm.chwijuntime.dto.tipstorage.TipsStorageResDto;
import com.gsm.chwijuntime.dto.tipstorage.TipsStorageSaveDto;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.service.member.MemberService;
import com.gsm.chwijuntime.service.tag.TagService;
import com.gsm.chwijuntime.service.tipstorage.TipsStorageService;
import com.gsm.chwijuntime.util.GetUserEmailUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.config.location=" +
        "classpath:/application.yml" +
        ",classpath:/key.yml"
)
@Transactional
public class TipStorageServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    TipsStorageService tipsStorageService;
    @Autowired
    TagService tagService;
    @Autowired
    GetUserEmailUtil getUserEmailUtil;

    @Test
    public void 회원가입_팁_저장(){
        log.info("회원가입 실행");
        MemberJoinDto memberJoinDto = MemberJoinDto.builder()
                .memberEmail("llmm030506@gmail.com")
                .memberPassword("1234")
                .memberClassNumber("2101")
                .build();
        memberService.InsertMember(memberJoinDto);
    }

}
