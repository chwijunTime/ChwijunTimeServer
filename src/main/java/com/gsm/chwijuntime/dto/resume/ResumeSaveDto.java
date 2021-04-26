package com.gsm.chwijuntime.dto.resume;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.MemberResume;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeSaveDto {

    private String resumeFileURL;

    public MemberResume toEntityByMember(Member member) {
        return MemberResume.builder()
                .member(member)
                .resumeFileURL(this.resumeFileURL)
                .build();
    }
}
