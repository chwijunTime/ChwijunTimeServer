package com.gsm.chwijuntime.dto.employmentAnnouncement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentAnnouncementUpdateDto {

    @NotBlank(message = "공고 회사 이름을 입력해주세요.")
    private String employmentAnnouncementName;

    @NotBlank(message = "채용 분야를 입력해주세요.")
    private String recruitmentField;

    @NotBlank(message = "회사 설명을 간단하게 입력해주세요.")
    private String employmentAnnouncementExplanation;

    @NotBlank(message = "우대 조건을 입력해주세요.")
    private String preferentialConditions;

    @NotBlank(message = "회사 위치를 입력해주세요.")
    private String employmentAnnouncementAddress;

    private String employmentAnnouncementEtc;

}
