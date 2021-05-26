package com.gsm.chwijuntime.model;

import com.gsm.chwijuntime.dto.contractingcompany.ContractionCompanyUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 협약 업체 테이블
public class ContractingCompany {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractingCompanyIdx;

    @Column(nullable = false)
    private String contractingCompanyName;

    @Column(nullable = false)
    private String contractingBusinessAreas;

    @Column(nullable = false)
    private String contractingCompanyAddress;

    @Column(nullable = false)
    private String contractingCompanyAboutUs;

    private String contractingCompanyAverageAnnualSalary;

    // ================== 외래키(연관관계 주인) ================== //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MemberIdx")
    private Member member;

    public void changeContractingCompany(ContractionCompanyUpdateDto contractionCompanyUpdateDto){
        this.contractingCompanyName = contractionCompanyUpdateDto.getContractingCompanyName();
        this.contractingBusinessAreas = contractionCompanyUpdateDto.getContractingBusinessAreas();
        this.contractingCompanyAddress = contractionCompanyUpdateDto.getContractingCompanyAddress();
        this.contractingCompanyAboutUs = contractionCompanyUpdateDto.getContractingCompanyAboutUs();
        this.contractingCompanyAverageAnnualSalary = contractionCompanyUpdateDto.getContractingCompanyAverageAnnualSalary();
    }
}
