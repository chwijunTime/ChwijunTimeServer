package com.gsm.chwijuntime.dto.resume;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeUpdateDto {

    @NotBlank(message = "이력서 URL을 입력해주세요.")
    private String resumeFileURL;

}
