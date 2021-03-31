package com.gsm.chwijuntime.dto.contractingcompany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.chwijuntime.model.ContractingCompany;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.tagmapping.MemberTag;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractingCompanySaveDto {

    private String contractingCompanyName;
    private String contractingBusinessAreas;
    private String contractingCompanyAddress;
    private String contractingCompanyAboutUs;
    private String contractingCompanyAverageAnnualSalary;


    public ContractingCompany ToEntityByContractingCompany(Member member) {
        return ContractingCompany.builder()
                .contractingCompanyName(this.contractingCompanyName)
                .contractingBusinessAreas(this.contractingBusinessAreas)
                .contractingCompanyAddress(this.contractingCompanyAddress)
                .contractingCompanyAboutUs(this.contractingCompanyAboutUs)
                .contractingCompanyAverageAnnualSalary(this.contractingCompanyAverageAnnualSalary)
                .member(member)
                .build();
    }

}
