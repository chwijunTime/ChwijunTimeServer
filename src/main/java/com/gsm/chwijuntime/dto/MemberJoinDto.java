package com.gsm.chwijuntime.dto;

import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.MemberGender;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberJoinDto {

    private String memberEmail;
    private String memberPassword;
    private MemberGender memberGender;
    private String memberClassNumber;

    public Member ToEntity() {
        return Member.builder()
                .memberEmail(this.memberEmail)
                .memberPassword(this.memberPassword)
                .memberGender(this.memberGender)
                .memberClassNumber(this.memberClassNumber)
                .build();
    }
}
