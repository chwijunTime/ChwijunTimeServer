package com.gsm.chwijuntime.dto.employmentconfirmation;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.chwijuntime.model.EmploymentConfirmation;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.model.tagmapping.EmploymentConfirmationTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmploymentConfirmationUpdateDto {

    @NotBlank(message = "채용 확정 회사 이름을 입력해주세요.")
    private String employmentConfirmationName;

    @NotBlank(message = "회사 위치를 입력해주세요.")
    private String employmentConfirmationAreas;

    @NotBlank(message = "학생 이름을 입력해주세요.")
    private String studentName;

    @NotBlank(message = "회사 주소를 입력해주세요.")
    private String employmentConfirmationAddress;

    private String employmentConfirmationSite;
    private String employmentConfirmationEtc;

    @NotEmpty(message = "태그를 1개 이상 등록해주세요.")
    private List<String> tagName;

    @JsonIgnore
    private Tag tag;
    @JsonIgnore
    private EmploymentConfirmation employmentConfirmation;

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
