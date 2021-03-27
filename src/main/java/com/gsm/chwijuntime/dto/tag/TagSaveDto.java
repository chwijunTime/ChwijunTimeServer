package com.gsm.chwijuntime.dto.tag;

import com.gsm.chwijuntime.model.Tag;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagSaveDto {

    private String tagName;

    public Tag toEntity(){
        return Tag.builder()
                .tagName(this.tagName)
                .build();
    }
}
