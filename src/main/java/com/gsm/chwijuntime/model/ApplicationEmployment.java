package com.gsm.chwijuntime.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// 취업 공고 신청 테이블
public class ApplicationEmployment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ApplicationEmploymentIdx;

    @Column(nullable = false)
    private String GitHubURL;

    @Enumerated(EnumType.STRING)
    private ApplicationEmploymentStatus applicationEmploymentStatus;

    @Column(nullable = false)
    private String ApplicationEmploymentResumeURL;

    // ================ 외래키(연관관계 주인) =================== //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MemberIdx")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EmploymentAnnouncementIdx")
    private EmploymentAnnouncement employmentAnnouncement;

    public void changeEmploymentAnnouncement(EmploymentAnnouncement employmentAnnouncement){
        this.employmentAnnouncement = employmentAnnouncement;
    }

}