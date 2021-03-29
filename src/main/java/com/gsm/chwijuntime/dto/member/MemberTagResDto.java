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
public class MemberTagResDto {

    private Member member;
    private List<Tag> tags;

    public MemberTagResDto mapping(Member member, List<Tag> tags) {
        return MemberTagResDto.builder()
                .member(member)
                .tags(tags)
                .build();
    }
}
