package com.gsm.chwijuntime.model;

import com.gsm.chwijuntime.model.tagmapping.EmploymentAnnouncementTag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
// 취업 공고 테이블
public class EmploymentAnnouncement {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employmentAnnouncementIdx;

    private LocalDateTime announcementDate;

    @Column(nullable = false)
    private String employmentAnnouncementName;

    @Column(nullable = false)
    private String recruitmentField;

    @Column(nullable = false)
    private String employmentAnnouncementExplanation;

    private String preferentialConditions;

    @Column(nullable = false)
    private String employmentAnnouncementAddress;

    @Column(nullable = false)
    private LocalDateTime deadLine;

    private String employmentAnnouncementEtc;


    // ============== 연관관계 노예 ================== //
    @OneToMany(mappedBy = "employmentAnnouncement", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ApplicationEmployment> applicationEmployments = new ArrayList<>();

    // ============== 연관관계 평의 메소드 ============== //
    public void addApplicationEmployment(ApplicationEmployment applicationEmployment){
        this.applicationEmployments.add(applicationEmployment);
        applicationEmployment.changeEmploymentAnnouncement(this);
    }


}