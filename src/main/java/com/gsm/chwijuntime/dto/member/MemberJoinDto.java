package com.gsm.chwijuntime.dto.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.Tag;
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


    public Member ToEntityByMember() {
        return Member.builder()
                .memberEmail(this.memberEmail)
                .memberPassword(this.memberPassword)
                .memberClassNumber(this.memberClassNumber)
                .roles(Collections.singletonList("ROLE_User"))
                .build();
    }
}
