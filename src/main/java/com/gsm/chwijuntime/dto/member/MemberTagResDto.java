package com.gsm.chwijuntime.dto.member;

import com.gsm.chwijuntime.model.Member;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberTagResDto {

    private Member member;
    private List<String> memberTags = new ArrayList<>();

    public static MemberTagResDto mapping(Member member, List<String> tagName) {
        return MemberTagResDto.builder()
                .member(member)
                .memberTags(tagName)
                .build();
    }
}
