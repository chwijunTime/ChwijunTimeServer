package com.gsm.chwijuntime.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginResDto {

    private String memberEmail;
    private String memberClassNumber;
    private String roles;
    private String accessToken;
    private String refreshToken;

    public static MemberLoginResDto mapping(String memberEmail, String memberClassNumber, String roles, String accessToken, String refreshToken) {
        return MemberLoginResDto.builder()
                .memberEmail(memberEmail)
                .memberClassNumber(memberClassNumber)
                .roles(roles)
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .build();
    }
}
