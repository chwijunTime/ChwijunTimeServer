package com.gsm.chwijuntime.dto.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.model.tagmapping.MemberTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberProfileSaveDto {

    @NotEmpty(message = "태그를 1개 이상 등록해주세요.")
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
