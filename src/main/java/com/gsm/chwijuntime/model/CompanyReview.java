package com.gsm.chwijuntime.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyReview {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyReviewIdx;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private LocalDate companyDateofApplication;

    @Column(nullable = false)
    private String companyAddress;

    @Column(nullable = false)
    private String companyReviews;

    @Column(nullable = false)
    private String companyFrequentlyAskedQuestions;

    private int companyCost;

    // =============== 외래키(연관관계 주인) ================= //
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MemberIdx")
    private Member member;


    // ============== 비즈니스 로직 ==================== //

}
