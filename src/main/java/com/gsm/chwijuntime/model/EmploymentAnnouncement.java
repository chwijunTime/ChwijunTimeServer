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
    private String employmentAnnouncementName;

    @Column(nullable = false)
    private String recruitmentField;

    @Column(length = 10000)
    private String employmentAnnouncementExplanation;

    private String preferentialConditions;

    @Column(nullable = false)
    private String employmentAnnouncementAddress;

    @Column(nullable = false)
    private LocalDate deadLine;

    private String employmentAnnouncementEtc;

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