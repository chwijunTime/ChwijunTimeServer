package com.gsm.chwijuntime.dto.member;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRefreshDto {

    private String refreshToken;

}
