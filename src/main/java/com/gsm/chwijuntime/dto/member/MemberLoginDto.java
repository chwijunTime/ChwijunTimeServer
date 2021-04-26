package com.gsm.chwijuntime.dto.member;

import lombok.*;

import javax.validation.constraints.Email;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberLoginDto {

    @Email(message = "이메일 형식으로 입력해주세요.")
    private String MemberEmail;
    private String MemberPassword;

}
