package com.gsm.chwijuntime.dto.employmentconfirmation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmploymentConfirmationResDto {

    private Long employmentConfirmationIdx;
    private String employmentConfirmationName;
    private String employmentConfirmationAreas;
    private String employmentConfirmationClassNumber;
    private String employmentConfirmationAddress;
    private String employmentConfirmationSite;
    private String employmentConfirmationEtc;

    private List<String> EmploymentConfirmationTags = new ArrayList<>();

}
