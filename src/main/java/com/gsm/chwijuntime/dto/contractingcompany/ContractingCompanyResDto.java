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
public class ContractingCompanyResDto {

    private Long contractingCompanyIdx;
    private String contractingCompanyName;
    private String contractingBusinessAreas;
    private String contractingCompanyAddress;
    private String contractingCompanyAboutUs;
    private String contractingCompanyAverageAnnualSalary;

}
