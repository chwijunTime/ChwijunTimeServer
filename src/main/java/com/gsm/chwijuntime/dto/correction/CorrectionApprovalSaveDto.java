package com.gsm.chwijuntime.dto.correction;

import com.gsm.chwijuntime.model.Correction;
import com.gsm.chwijuntime.model.CorrectionApply;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CorrectionApprovalSaveDto {

    @NotBlank(message = "학번을 입력해주세요.")
    private String classNumber;

    @NotBlank(message = "첨삭을 입력해주세요.")
    private String correctionContent;

    public Correction toEntityByApproval(CorrectionApply correctionApply){
        return Correction.builder()
                .correctionContent(this.correctionContent)
                .correctionApply(correctionApply)
                .classNumber(this.classNumber)
                .build();
    }
}
