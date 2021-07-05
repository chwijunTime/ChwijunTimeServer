package com.gsm.chwijuntime.dto.companyreview;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.chwijuntime.model.CompanyReview;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.model.tagmapping.CompanyReviewTag;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyUpdateDto {

    @NotBlank(message = "회사 이름을 입력해주세요.")
    private String companyName;

    @PastOrPresent(message = "미래의 날짜를 입력하지 말아주세요.")
    private LocalDate companyDateofApplication;

    @NotBlank(message = "회사 주소를 입력해주세요.")
    private String companyAddress;

    @NotBlank(message = "리뷰를 입력해주세요")
    private String companyReviews;

    @NotBlank(message = "자주 물어본 질문을 입력해주세요.")
    private String companyFrequentlyAskedQuestions;

    private int companyCost; // 면접 과정중 사용된 비용

    @NotEmpty(message = "태그를 1개 이상 등록해주세요.")
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
