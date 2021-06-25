package com.gsm.chwijuntime.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
// 취업 공고 신청 테이블
public class ApplicationEmployment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationEmploymentIdx;

    @Column(nullable = false)
    private String gitHubURL;  // 깃허브 URL

    @Column(nullable = false)
    private String applicationEmploymentResumeURL; // 이력서 URL (Notion)

    @Column(nullable = false)
    private String applicationEmploymentPortfolioURL;  // 포트폴리오 URL (Notion)

    @Enumerated(EnumType.STRING)
    private ApplicationEmploymentStatus applicationEmploymentStatus;  //신청 상태

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate applicationDate;  //지원 날짜

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