package com.gsm.chwijuntime.dto.tag;

import com.gsm.chwijuntime.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TagSaveDto {

    private String tagName;

    public Tag toEntity(){
        return Tag.builder()
                .tagName(this.tagName)
                .build();
    }
}
