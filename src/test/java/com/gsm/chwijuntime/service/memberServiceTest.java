package com.gsm.chwijuntime.service;

import com.gsm.chwijuntime.advice.exception.UserDuplicationException;
import com.gsm.chwijuntime.dto.MemberJoinDto;
import com.gsm.chwijuntime.repository.MemberRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class memberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Before
    public void 회원가입() throws Exception {
        MemberJoinDto memberJoinDto = MemberJoinDto.builder()
                .memberEmail("llmm030506@gmail.com")
                .memberPassword("1234")
                .memberClassNumber("2101")
                .build();
        memberService.InsertMember(memberJoinDto);
    }

    @Test(expected = UserDuplicationException.class)
    public void 중복_처리() throws Exception {
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

}
