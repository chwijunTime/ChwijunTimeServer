package com.gsm.chwijuntime.dto.employmentAnnouncement;

import com.gsm.chwijuntime.model.EmploymentAnnouncement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentAnnouncementSaveDto {

    private LocalDateTime announcementDate;   //공고일
    private String employmentAnnouncementName;
    private String recruitmentField;    //채용 분야
    private String employmentAnnouncementExplanation;  //회사 설명
    private String preferentialConditions; //우대 조건
    private String employmentAnnouncementAddress; //회사 위치
    private LocalDateTime deadLine;
    private String employmentAnnouncementEtc; //기타 설명

    public EmploymentAnnouncement toEntityByEmploymentAnnouncement() {
        return EmploymentAnnouncement.builder()
                .announcementDate(this.announcementDate)
                .employmentAnnouncementName(this.employmentAnnouncementName)
                .recruitmentField(this.recruitmentField)
                .employmentAnnouncementExplanation(this.employmentAnnouncementExplanation)
                .preferentialConditions(this.preferentialConditions)
                .employmentAnnouncementAddress(this.employmentAnnouncementAddress)
                .deadLine(this.deadLine)
                .employmentAnnouncementEtc(this.employmentAnnouncementEtc)
                .build();
    }
}
