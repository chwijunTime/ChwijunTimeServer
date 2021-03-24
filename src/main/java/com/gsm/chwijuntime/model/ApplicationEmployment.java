package com.gsm.chwijuntime.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MemberIdx")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "EmploymentAnnouncementIdx")
    private EmploymentAnnouncement employmentAnnouncement;

}
