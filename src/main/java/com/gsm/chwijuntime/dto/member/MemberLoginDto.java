package com.gsm.chwijuntime.dto.member;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberLoginDto {

    private String MemberEmail;
    private String MemberPasword;

}
