package com.gsm.chwijuntime.dto.employmentconfirmation;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmploymentConfirmationResDto {

    private Long employmentConfirmationIdx;
    private String employmentConfirmationName;
    private String employmentConfirmationAreas;
    private String studentName;
    private String EmploymentConfirmationGeneration;
    private String employmentConfirmationAddress;
    private String employmentConfirmationSite;
    private String employmentConfirmationEtc;

    private List<String> EmploymentConfirmationTags = new ArrayList<>();

}
