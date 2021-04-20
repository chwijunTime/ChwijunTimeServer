package com.gsm.chwijuntime.dto.employmentAnnouncement;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmploymentAnnouncementResponseDto {

    private Long employmentAnnouncementIdx;
    private LocalDate announcementDate;
    private String employmentAnnouncementName;
    private String recruitmentField;
    private String employmentAnnouncementExplanation;
    private String preferentialConditions;
    private String employmentAnnouncementAddress;
    private LocalDate deadLine;
    private String employmentAnnouncementEtc;

}
