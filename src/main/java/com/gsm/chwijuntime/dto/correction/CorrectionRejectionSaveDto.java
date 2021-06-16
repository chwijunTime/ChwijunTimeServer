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
public class CorrectionRejectionSaveDto {

    @NotBlank(message = "거절 이유를 입력해주세요.")
    private String reasonForRejection;

    @Size(max = 4, min = 4, message = "학번은 4글자로 입력해주세요")
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
