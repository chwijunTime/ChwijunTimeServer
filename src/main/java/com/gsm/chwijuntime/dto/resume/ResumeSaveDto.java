package com.gsm.chwijuntime.dto.resume;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.MemberResume;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeSaveDto {

    @NotBlank(message = "이력서 URL을 입력해주세요.")
    private String resumeFileURL;

    public MemberResume toEntityByMember(Member member) {
        return MemberResume.builder()
                .member(member)
                .resumeFileURL(this.resumeFileURL)
                .build();
    }
}
