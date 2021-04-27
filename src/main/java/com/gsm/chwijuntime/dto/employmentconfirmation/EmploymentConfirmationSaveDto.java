package com.gsm.chwijuntime.dto.employmentconfirmation;

import com.gsm.chwijuntime.model.EmploymentConfirmation;
import com.gsm.chwijuntime.model.Member;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmploymentConfirmationSaveDto {

    private String employmentConfirmationName;
    private String employmentConfirmationAreas;
    private String employmentConfirmationClassNumber;
    private String employmentConfirmationAddress;
    private String employmentConfirmationSite;
    private String employmentConfirmationEtc;

    public EmploymentConfirmation toEntityByEmploymentConfirmation(Member member) {
        return EmploymentConfirmation.builder()
                .employmentConfirmationName(this.employmentConfirmationName)
                .employmentConfirmationAreas(this.employmentConfirmationAreas)
                .employmentConfirmationClassNumber(this.employmentConfirmationClassNumber)
                .employmentConfirmationAddress(this.employmentConfirmationAddress)
                .employmentConfirmationSite(this.employmentConfirmationSite)
                .employmentConfirmationEtc(this.employmentConfirmationEtc)
                .member(member)
                .build();
    }
}
