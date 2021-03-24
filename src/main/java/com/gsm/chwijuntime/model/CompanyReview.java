package com.gsm.chwijuntime.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyReview {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CompanyReviewIdx;

    @Column(nullable = false)
    private String CompanyName;

    @Column(nullable = false)
    private LocalDateTime CompanyDateofApplication;

    @Column(nullable = false)
    private String CompanyAddress;

    @Column(nullable = false)
    private String CompanyReview;

    @Column(nullable = false)
    private String CompanyFrequentlyAskedQuestions;

    private int CompanyCost;

    // =============== 외래키(연관관계 주인) ================= //
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MemberIdx")
    private Member member;
}
