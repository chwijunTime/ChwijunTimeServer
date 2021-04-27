package com.gsm.chwijuntime.dto.employmentAnnouncement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.chwijuntime.model.ContractingCompany;
import com.gsm.chwijuntime.model.EmploymentAnnouncement;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.model.tagmapping.EmploymentAnnouncementTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentAnnouncementSaveDto {

    @JsonIgnore
    private LocalDate announcementDate;   //공고일

    private String employmentAnnouncementName;
    private String recruitmentField;    //채용 분야
    private String employmentAnnouncementExplanation;  //회사 설명
    private String preferentialConditions; //우대 조건
    private String employmentAnnouncementAddress; //회사 위치
    private LocalDate deadLine;  //공고 마감일
    private String employmentAnnouncementEtc; //기타 설명
    private List<String> tagName;

    @JsonIgnore
    private Tag tag;
    @JsonIgnore
    private EmploymentAnnouncement employmentAnnouncement;

    public EmploymentAnnouncement toEntityByEmploymentAnnouncement(Member member) {
        return EmploymentAnnouncement.builder()
                .member(member)
                .announcementDate(LocalDate.now())
                .employmentAnnouncementName(this.employmentAnnouncementName)
                .recruitmentField(this.recruitmentField)
                .employmentAnnouncementExplanation(this.employmentAnnouncementExplanation)
                .preferentialConditions(this.preferentialConditions)
                .employmentAnnouncementAddress(this.employmentAnnouncementAddress)
                .deadLine(this.deadLine)
                .employmentAnnouncementEtc(this.employmentAnnouncementEtc)
                .build();
    }
    public void MappingTagByEmploymentAnnouncement(Tag tag, EmploymentAnnouncement employmentAnnouncement){
        this.tag = tag;
        this.employmentAnnouncement = employmentAnnouncement;
    }

    public EmploymentAnnouncementTag ToEntityByEmploymentAnnouncementTag(){
        return EmploymentAnnouncementTag.builder()
                .employmentAnnouncement(this.employmentAnnouncement)
                .tag(this.tag)
                .build();
    }

}
