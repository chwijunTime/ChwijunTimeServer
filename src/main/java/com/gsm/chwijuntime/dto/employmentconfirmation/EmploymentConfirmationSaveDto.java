package com.gsm.chwijuntime.dto.employmentconfirmation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.chwijuntime.model.EmploymentConfirmation;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.model.tagmapping.EmploymentConfirmationTag;
import lombok.*;

import java.util.List;

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
    private List<String> tagName;

    @JsonIgnore
    private Tag tag;
    @JsonIgnore
    private EmploymentConfirmation employmentConfirmation;

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

    public void mappingTagEmploymentConfirmation(Tag tag, EmploymentConfirmation employmentConfirmation){
        this.tag = tag;
        this.employmentConfirmation = employmentConfirmation;
    }

    public EmploymentConfirmationTag toEntityByEmploymentConfirmationTag(){
        return EmploymentConfirmationTag.builder()
                .employmentConfirmation(this.employmentConfirmation)
                .tag(this.tag)
                .build();
    }
}
