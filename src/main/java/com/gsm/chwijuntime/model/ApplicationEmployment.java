package com.gsm.chwijuntime.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 취업 공고 신청 테이블
public class ApplicationEmployment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationEmploymentIdx;

    @Column(nullable = false)
    private String gitHubURL;

    @Column(nullable = false)
    private String applicationEmploymentResumeURL;

    @Column(nullable = false)
    private String applicationEmploymentPortfolioURL;

    @Enumerated(EnumType.STRING)
    private ApplicationEmploymentStatus applicationEmploymentStatus;

    // ================ 외래키(연관관계 주인) =================== //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MemberIdx")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EmploymentAnnouncementIdx")
    private EmploymentAnnouncement employmentAnnouncement;


    // =============== 비즈니스 로직 =========================== //

    public void changeApplicationEmploymentStatusApprove(){
        this.applicationEmploymentStatus = ApplicationEmploymentStatus.Approve;
    }

    public void changeApplicationEmploymentStatusApproveReject() {
        this.applicationEmploymentStatus = ApplicationEmploymentStatus.Reject;
    }
}