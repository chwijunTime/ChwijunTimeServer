package com.gsm.chwijuntime.dto.companyreview;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyReviewResDto {

    private Long companyReviewIdx;
    private String companyName;
    private LocalDate companyDateofApplication;
    private String companyAddress;
    private String companyReviews;
    private String companyFrequentlyAskedQuestions;
    private int companyCost;

}
