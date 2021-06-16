package com.gsm.chwijuntime.model;

import com.gsm.chwijuntime.dto.employmentAnnouncement.EmploymentAnnouncementUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 취업 공고 테이블
public class EmploymentAnnouncement {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employmentAnnouncementIdx;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate announcementDate;    //공고 등록 날짜

    @Column(nullable = false)
    private String employmentAnnouncementName;  //회사명

    @Column(nullable = false)
    private String recruitmentField;   //채용 분야

    @Column(length = 30000)
    private String employmentAnnouncementExplanation; //회사 설명

    @Column(length = 10000)
    private String preferentialConditions;   //우대 조건

    @Column(nullable = false)
    private String employmentAnnouncementAddress;   // 회사 위치

    @Column(nullable = false)
    private LocalDate deadLine;

    @Column(length = 10000)
    private String employmentAnnouncementEtc;   //기타

    // ================== 외래키(연관관계 주인) ================== //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MemberIdx")
    private Member member;

    public void update(EmploymentAnnouncementUpdateDto employmentAnnouncementUpdateDto){
        this.employmentAnnouncementName = employmentAnnouncementUpdateDto.getEmploymentAnnouncementName();
        this.recruitmentField = employmentAnnouncementUpdateDto.getRecruitmentField();
        this.employmentAnnouncementExplanation = employmentAnnouncementUpdateDto.getEmploymentAnnouncementExplanation();
        this.preferentialConditions = employmentAnnouncementUpdateDto.getPreferentialConditions();
        this.employmentAnnouncementAddress = employmentAnnouncementUpdateDto.getEmploymentAnnouncementAddress();
        this.employmentAnnouncementEtc = employmentAnnouncementUpdateDto.getEmploymentAnnouncementEtc();
    }
}