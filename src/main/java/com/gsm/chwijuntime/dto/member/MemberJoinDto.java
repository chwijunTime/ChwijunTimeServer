package com.gsm.chwijuntime.dto.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.chwijuntime.advice.exception.NotFoundApplicationEmploymentException;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.Tag;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Collections;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberJoinDto {

    @Email(message = "이메일 형식으로 입력해주세요.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String memberEmail;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&]).{8,15}.$", message = "숫자, 문자, 특수문자 모두 포함 (8~15자)로 입력해주세요.")
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String memberPassword;
    
    @Size(max = 4, min = 4, message = "학번은 4글자로 입력해주세요")
    @NotBlank(message = "학번을 입력해주세요.")

    private String memberClassNumber;
    public Member ToEntityByMember() {
        return Member.builder()
                .memberCreated(LocalDateTime.now())
                .memberEmail(this.memberEmail)
                .memberPassword(this.memberPassword)
                .memberClassNumber(this.memberClassNumber)
                .roles(Collections.singletonList("ROLE_User"))
                .build();
    }
}
