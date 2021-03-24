package com.gsm.chwijuntime.model;

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
public class EmploymentAnnouncement {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long EmploymentAnnouncementIdx;

    private LocalDateTime AnnouncementDate;

    @Column(nullable = false)
    private String EmploymentAnnouncementName;

    @Column(nullable = false)
    private String RecruitmentField;

    @Column(nullable = false)
    private String EmploymentAnnouncementExplanation;

    private String PreferentialConditions;

    @Column(nullable = false)
    private String EmploymentAnnouncementAddress;

    @Column(nullable = false)
    private LocalDateTime DeadLine;

    private String EmploymentAnnouncementEtc;


    // ============== 연관관계 노예 ================== //
    @OneToMany(mappedBy = "employmentAnnouncement", fetch = FetchType.LAZY)
    private List<ApplicationEmployment> applicationEmployments = new ArrayList<>();
}
