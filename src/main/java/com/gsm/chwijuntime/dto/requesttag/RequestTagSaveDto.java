package com.gsm.chwijuntime.dto.requesttag;

import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.RequestTag;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestTagSaveDto {

    @NotBlank(message = "태그 이름을 입력해주세요.")
    private String tagName;

    public RequestTag toEntityRequestTag(Member member) {
        return RequestTag.builder()
                .tagName(this.tagName)
                .member(member)
                .build();
    }
}
