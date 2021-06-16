package com.gsm.chwijuntime.model;

import com.gsm.chwijuntime.dto.companyreview.CompanyUpdateDto;
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
    private String companyName;  //회사 이름

    private LocalDate companyDateofApplication;  //지원 날짜

    @Column(nullable = false)
    private String companyAddress;  //회사 주소

    @Column(nullable = false, length = 50000)
    private String companyReviews; //회사 리뷰

    @Column(nullable = false, length = 50000)
    private String companyFrequentlyAskedQuestions;  //자주 나온 질문

    private int companyCost;  //사용한 비용

    // =============== 외래키(연관관계 주인) ================= //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MemberIdx")
    private Member member;


    // ============== 비즈니스 로직 ==================== //
    public void changeCompanyReview(CompanyUpdateDto companyUpdateDto){
        this.companyName = companyUpdateDto.getCompanyName();
        this.companyDateofApplication = companyUpdateDto.getCompanyDateofApplication();
        this.companyAddress = companyUpdateDto.getCompanyAddress();
        this.companyReviews = companyUpdateDto.getCompanyReviews();
        this.companyFrequentlyAskedQuestions = companyUpdateDto.getCompanyFrequentlyAskedQuestions();
        this.companyCost = companyUpdateDto.getCompanyCost();
    }
}
