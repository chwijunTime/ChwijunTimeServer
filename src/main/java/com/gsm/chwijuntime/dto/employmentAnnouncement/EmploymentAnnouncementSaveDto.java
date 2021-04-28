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

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentAnnouncementSaveDto {

    @JsonIgnore
    private LocalDate announcementDate;   //공고일

    @NotBlank(message = "공고 회사 이름을 입력해주세요.")
    private String employmentAnnouncementName;

    @NotBlank(message = "채용 분야를 입력해주세요.")
    private String recruitmentField;    //채용 분야

    @NotBlank(message = "회사 설명을 간단하게 입력해주세요.")
    private String employmentAnnouncementExplanation;  //회사 설명

    @NotBlank(message = "우대 조건을 입력해주세요.")
    private String preferentialConditions; //우대 조건

    @NotBlank(message = "회사 위치를 입력해주세요.")
    private String employmentAnnouncementAddress; //회사 위치

    @FutureOrPresent(message = "현재 시간보다 미래 날짜를 입력해주세요.")
    private LocalDate deadLine;  //공고 마감일

    private String employmentAnnouncementEtc; //기타 설명

    @NotEmpty(message = "태그를 1개 이상 등록해주세요.")
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
