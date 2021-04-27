package com.gsm.chwijuntime.dto.employmentAnnouncement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentAnnouncementUpdateDto {

    private String employmentAnnouncementName;
    private String recruitmentField;
    private String employmentAnnouncementExplanation;
    private String preferentialConditions;
    private String employmentAnnouncementAddress;
    private String employmentAnnouncementEtc;

}
