package com.gsm.chwijuntime.dto.contractingcompany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.chwijuntime.model.ContractingCompany;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.model.tagmapping.ContractingCompanyTag;
import com.gsm.chwijuntime.model.tagmapping.MemberTag;
import lombok.*;

import java.util.List;

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
    private List<String> tagName;

    @JsonIgnore
    private Tag tag;
    @JsonIgnore
    private ContractingCompany contractingCompany;

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

    public void MappingTag_ContractingCompany(Tag tag, ContractingCompany contractingCompany){
        this.tag = tag;
        this.contractingCompany = contractingCompany;
    }

    public ContractingCompanyTag ToEntityByContractingCompanyTag() {
        return ContractingCompanyTag.builder()
                .contractingCompany(this.contractingCompany)
                .tag(this.tag)
                .build();
    }

}
