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

    private String MemberEmail;
    private String MemberPassword;

}
