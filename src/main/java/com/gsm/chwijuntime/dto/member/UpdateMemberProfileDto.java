package com.gsm.chwijuntime.dto.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.model.tagmapping.MemberTag;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateMemberProfileDto {

    private String memberPhoneNumber;
    private String memberETC;
    private List<String> tagName;

    @JsonIgnore
    private Tag tag;
    @JsonIgnore
    private Member member;

    public void MappingTag_Member(Tag tag, Member member){
        this.tag = tag;
        this.member = member;
    }

    public MemberTag ToEntityByMemberTag() {
        return MemberTag.builder()
                .member(this.member)
                .tag(this.tag)
                .build();
    }
}
