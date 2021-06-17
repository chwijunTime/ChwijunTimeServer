package com.gsm.chwijuntime.dto.contractingcompany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.chwijuntime.model.ContractingCompany;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.model.tagmapping.ContractingCompanyTag;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractingCompanySaveDto {

    @NotBlank(message = "회사 이름을 입력해주세요.")
    private String contractingCompanyName;

    @NotBlank(message = "사업 분야를 입력해주세요.")
    private String contractingBusinessAreas;

    @NotBlank(message = "회사 위치를 입력해주세요.")
    private String contractingArea;

    @NotBlank(message = "회사의 정확한 주소를 입력해주세요.")
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
                .contractingArea(this.contractingArea)
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
