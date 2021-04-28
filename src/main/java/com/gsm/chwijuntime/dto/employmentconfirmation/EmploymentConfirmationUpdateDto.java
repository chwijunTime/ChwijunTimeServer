package com.gsm.chwijuntime.dto.employmentconfirmation;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmploymentConfirmationUpdateDto {

    @NotBlank(message = "채용 확정 회사 이름을 입력해주세요.")
    private String employmentConfirmationName;

    @NotBlank(message = "회사 위치를 입력해주세요.")
    private String employmentConfirmationAreas;

    @NotBlank(message = "회사 주소를 입력해주세요.")
    private String employmentConfirmationAddress;

    @NotBlank(message = "회사 사이트를 입력해주세요.")
    private String employmentConfirmationSite;

    private String employmentConfirmationEtc;

}
