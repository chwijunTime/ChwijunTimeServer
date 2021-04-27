package com.gsm.chwijuntime.dto.companyreview;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.chwijuntime.model.CompanyReview;
import com.gsm.chwijuntime.model.ContractingCompany;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.model.tagmapping.CompanyReviewTag;
import com.gsm.chwijuntime.model.tagmapping.ContractingCompanyTag;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private List<String> tagName = new ArrayList<>();

    @JsonIgnore
    private Tag tag;
    @JsonIgnore
    private CompanyReview companyReview;

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

    public void MappingTag_ContractingCompany(Tag tag, CompanyReview companyReview){
        this.tag = tag;
        this.companyReview = companyReview;
    }

    public CompanyReviewTag ToEntityByCompanyReviewTag() {
        return CompanyReviewTag.builder()
                .companyReview(this.companyReview)
                .tag(this.tag)
                .build();
    }
}
