package com.gsm.chwijuntime.dto.contractingcompany;

import lombok.*;

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
