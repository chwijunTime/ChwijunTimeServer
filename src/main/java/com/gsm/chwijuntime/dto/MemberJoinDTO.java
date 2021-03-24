package com.gsm.chwijuntime.dto;

import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.MemberGender;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberJoinDTO {

    private String MemberEmail;
    private String MemberPassword;
    private MemberGender memberGender;
    private String MemberClassNumber;

    public Member ToEntity() {
        return Member.builder()
                .MemberEmail(this.MemberEmail)
                .MemberPassword(this.MemberPassword)
                .MemberGender(this.memberGender)
                .MemberClassNumber(this.MemberClassNumber)
                .build();
    }
}
