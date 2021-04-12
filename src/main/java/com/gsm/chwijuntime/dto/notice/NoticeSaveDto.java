package com.gsm.chwijuntime.dto.notice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeSaveDto {

    private String title;
    private String content;

    @JsonIgnore
    private LocalDateTime createTime = LocalDateTime.now();

    public Notice ToEntityByNotice(Member member) {
        return Notice.builder()
                .title(this.title)
                .content(this.content)
                .createDated(this.createTime)
                .member(member)
                .build();
    }
}
