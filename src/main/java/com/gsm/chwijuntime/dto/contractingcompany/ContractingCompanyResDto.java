package com.gsm.chwijuntime.dto.contractingcompany;

import lombok.*;

import java.util.ArrayList;
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
    private String location;
    private List<String> contractingCompanyTags = new ArrayList<>();

}
