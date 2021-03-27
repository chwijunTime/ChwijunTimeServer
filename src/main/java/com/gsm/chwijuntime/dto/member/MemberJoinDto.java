package com.gsm.chwijuntime.dto.member;

import com.gsm.chwijuntime.model.Member;
import lombok.*;

import java.util.Collections;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberJoinDto {

    private String memberEmail;
    private String memberPassword;
    private String memberClassNumber;

    public Member ToEntity() {
        return Member.builder()
                .memberEmail(this.memberEmail)
                .memberPassword(this.memberPassword)
                .memberClassNumber(this.memberClassNumber)
                .roles(Collections.singletonList("ROLE_User"))
                .build();
    }
}
