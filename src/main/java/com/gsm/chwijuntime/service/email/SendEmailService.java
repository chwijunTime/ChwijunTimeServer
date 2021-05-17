package com.gsm.chwijuntime.service.email;

import com.gsm.chwijuntime.advice.exception.CAuthenticationEntryPointException;
import com.gsm.chwijuntime.dto.email.MailDto;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SendEmailService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "s19066@gsm.hs.kr";

    @Transactional
    public MailDto createMailAndChangePassword(String userEmail){
        String str = getTempPassword();
        MailDto dto = new MailDto();
        dto.setAddress(userEmail);
        dto.setTitle(userEmail+"님의 취준타임 임시비밀번호 안내 이메일 입니다.");
        dto.setMessage("안녕하세요. 취준타임 임시비밀번호 안내 관련 이메일 입니다." + "[" + userEmail + "]" +"님의 임시 비밀번호는 "
                + str + " 입니다.");
        updatePassword(str,userEmail);
        return dto;
    }

    @Async
    @Transactional
    public void mailSend(MailDto mailDto){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setFrom(SendEmailService.FROM_ADDRESS);
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());
        mailSender.send(message);
    }

    @Transactional
    public void updatePassword(String str,String userEmail){
        String pw = passwordEncoder.encode(str);
        Member member = memberRepository.findByMemberEmail(userEmail).orElseThrow(null);
        member.change_password(pw);
    }

    @Transactional
    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }
}
