package com.gsm.chwijuntime.dto.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.chwijuntime.advice.exception.NotFoundApplicationEmploymentException;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.Tag;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Collections;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberJoinDto {

    @Email(message = "이메일 형식으로 입력해주세요.")
    private String memberEmail;
    private String memberPassword;
    
    @Size(max = 4, min = 4, message = "학번은 4글자로 입력해주세요")
    private String memberClassNumber;

    public Member ToEntityByMember() {
        return Member.builder()
                .memberEmail(this.memberEmail)
                .memberPassword(this.memberPassword)
                .memberClassNumber(this.memberClassNumber)
                .roles(Collections.singletonList("ROLE_User"))
                .build();
    }
}
