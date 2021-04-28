package com.gsm.chwijuntime.dto.tag;

import com.gsm.chwijuntime.model.Tag;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagSaveDto {

    @NotBlank(message = "태그 이름을 입력해주세요.")
    private String tagName;

    public Tag toEntity(){
        return Tag.builder()
                .tagName(this.tagName)
                .build();
    }
}
