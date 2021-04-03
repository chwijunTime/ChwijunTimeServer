package com.gsm.chwijuntime.dto.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.model.tagmapping.MemberTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberProfileSaveDto {

    private List<String> tagName;
    private String memberPhoneNumber;
    private String memberETC;

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
