package com.gsm.chwijuntime.dto.member;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRefreshDto {

    @NotBlank(message = "RefreshToken을 입력해주세요.")
    private String refreshToken;

}
