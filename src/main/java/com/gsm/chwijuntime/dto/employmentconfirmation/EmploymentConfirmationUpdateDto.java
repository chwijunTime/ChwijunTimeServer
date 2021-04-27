package com.gsm.chwijuntime.dto.employmentconfirmation;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmploymentConfirmationUpdateDto {

    private String employmentConfirmationName;
    private String employmentConfirmationAreas;
    private String employmentConfirmationAddress;
    private String employmentConfirmationSite;
    private String employmentConfirmationEtc;

}
