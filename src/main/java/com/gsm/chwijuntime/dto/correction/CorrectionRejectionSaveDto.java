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
public class CorrectionRejectionSaveDto {

    @NotBlank(message = "거절 이유를 입력해주세요.")
    private String reasonForRejection;

    @NotBlank(message = "학번을 입력해주세요.")
    private String classNumber;

    public Correction toEntityByApproval(CorrectionApply correctionApply){
        return Correction.builder()
                .correctionApply(correctionApply)
                .reasonForRejection(this.reasonForRejection)
                .classNumber(this.classNumber)
                .build();
    }

}
