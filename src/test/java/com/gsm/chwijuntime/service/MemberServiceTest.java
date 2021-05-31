package com.gsm.chwijuntime.service;

import com.gsm.chwijuntime.advice.exception.EmailNotFoundException;
import com.gsm.chwijuntime.advice.exception.IncorrectPasswordException;
import com.gsm.chwijuntime.advice.exception.UserDuplicationException;
import com.gsm.chwijuntime.dto.member.MemberJoinDto;
import com.gsm.chwijuntime.dto.member.MemberLoginDto;
import com.gsm.chwijuntime.repository.MemberRepository;
import com.gsm.chwijuntime.service.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(properties = "spring.config.location=" +
        "classpath:/application.yml" +
        ",classpath:/key.yml"
)
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Before
    public void 회원가입() throws Exception {
        log.info("회원가입 실행");
        MemberJoinDto memberJoinDto = MemberJoinDto.builder()
                .memberEmail("llmm030506@gmail.com")
                .memberPassword("1234")
                .memberClassNumber("2101")
                .build();
        memberService.InsertMember(memberJoinDto);
    }

    @Test(expected = UserDuplicationException.class)
    public void 중복_처리() throws Exception {
        log.info("회원 가입 중복 처리");
        //given
        MemberJoinDto memberJoinDto = MemberJoinDto.builder()
                .memberEmail("llmm030506@gmail.com")
                .memberPassword("1234")
                .memberClassNumber("2101")
                .build();
        //when
        memberService.InsertMember(memberJoinDto);

        //then
        fail("에외가 발생해야 한다.");
    }

    @Test(expected = EmailNotFoundException.class)
    public void 이메일_틀림() {
        log.info("이메일 틀림");
        //give
        MemberLoginDto memberLoginDto = MemberLoginDto.builder()
                .memberEmail("1234")
                .memberPassword("1234")
                .build();
        //when
        memberService.findMember(memberLoginDto);

        //then
        fail("예외가 발생해야 한다.");
    }

    @Test(expected = IncorrectPasswordException.class)
    public void 비밀번호_틀림() {
        log.info("비밀번호 틀림");
        //give
        MemberLoginDto memberLoginDto = MemberLoginDto.builder()
                .memberEmail("llmm030506@gmail.com")
                .memberPassword("12345")
                .build();
        //when
        memberService.findMember(memberLoginDto);

        //then
        fail("예외가 발생해야 한다.");
    }
}
