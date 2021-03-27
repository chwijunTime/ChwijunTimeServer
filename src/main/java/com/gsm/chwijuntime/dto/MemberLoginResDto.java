package com.gsm.chwijuntime.dto;

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

    public static MemberLoginResDto mapping(String memberEmail, String memberClassNumber, String roles, String accessToken) {
        return MemberLoginResDto.builder()
                .memberEmail(memberEmail)
                .memberClassNumber(memberClassNumber)
                .roles(roles)
                .accessToken(accessToken)
                .build();
    }
}
