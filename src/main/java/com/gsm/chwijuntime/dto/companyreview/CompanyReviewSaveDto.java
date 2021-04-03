package com.gsm.chwijuntime.dto.companyreview;

import com.gsm.chwijuntime.model.CompanyReview;
import com.gsm.chwijuntime.model.Member;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyReviewSaveDto {

    private String companyName;
    private LocalDate companyDateofApplication;
    private String companyAddress;
    private String companyReviews;
    private String companyFrequentlyAskedQuestions;
    private int companyCost; // 면접 과정중 사용된 비용


    public CompanyReview ToEntityByContractingCompany(Member member) {
        return CompanyReview.builder()
                .companyName(this.companyName)
                .companyDateofApplication(this.companyDateofApplication)
                .companyAddress(this.companyAddress)
                .companyReviews(this.companyReviews)
                .companyFrequentlyAskedQuestions(this.companyFrequentlyAskedQuestions)
                .companyCost(this.companyCost)
                .member(member)
                .build();
    }
}
