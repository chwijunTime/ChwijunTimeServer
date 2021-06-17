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
    private String contractingCompanyName;  //회사 이름

    @Column(nullable = false, length = 400)
    private String contractingBusinessAreas;  //사업 분야

    @Column(nullable = false)
    private String contractingCompanyAddress;  //회사 주소

    @Column(nullable = false)
    private String contractingArea;

    @Column(nullable = false, length = 10000)
    private String contractingCompanyAboutUs;  //회사 소개

    @Column(length = 2000)
    private String contractingCompanyAverageAnnualSalary; //연봉

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
