package com.gsm.chwijuntime.dto.correction;

import com.gsm.chwijuntime.model.Correction;
import com.gsm.chwijuntime.model.CorrectionApply;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CorrectionApprovalSaveDto {

    @Size(max = 4, min = 4, message = "학번은 4글자로 입력해주세요")
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
