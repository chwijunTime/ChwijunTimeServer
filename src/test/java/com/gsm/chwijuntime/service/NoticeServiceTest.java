package com.gsm.chwijuntime.service;

import com.gsm.chwijuntime.dto.member.MemberJoinDto;
import com.gsm.chwijuntime.dto.notice.NoticeSaveDto;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.repository.MemberRepository;
import com.gsm.chwijuntime.service.notice.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(properties = "spring.config.location=" +
        "classpath:/application.yml"
)
@Transactional
public class NoticeServiceTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private NoticeService noticeService;

    final String USER_ID = "adminID";
    final String USER_PASSWORD = "adminPW";
    final String USER_CLASS_NUMBER = "1234";

    // test 편의를 위한 회원가입 매서드
    void adminSignUp(String adminId, String password, String classNumber) throws Exception {
        System.out.println("NoticeServiceTest.adminSignUp");
        MemberJoinDto memberJoinDto = new MemberJoinDto(adminId, password, classNumber);
        memberRepository.save(memberJoinDto.ToEntityByMember());
    }

    //test 편의를 위한 로그인 매서드
    Member adminLogin(String adminId, String password) {
        System.out.println("NoticeServiceTest.adminLogin");
        Member member = memberRepository.findByMemberEmailAndMemberPassword(adminId, password);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                member.getMemberEmail(),
                member.getMemberPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(token);
        return member;
    }

    @Test
    public void saveNotice() throws Exception {
        System.out.println("NoticeServiceTest.saveNotice");
        //회원가입
        adminSignUp(USER_ID, USER_PASSWORD, USER_CLASS_NUMBER);

        //로그인
        adminLogin(USER_ID, USER_PASSWORD);

        //given
        NoticeSaveDto noticeSaveDto = NoticeSaveDto.builder()
                .title("title1")
                .content("content1")
                .build();
        
        //when
        noticeService.save(noticeSaveDto);

        //then
    }
}
