package com.gsm.chwijuntime.dto.member;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberLoginDto {

    @Email(message = "이메일 형식으로 입력해주세요.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String MemberEmail;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String MemberPassword;

}
