package com.gsm.chwijuntime.dto.correction;

import com.gsm.chwijuntime.model.Correction;
import com.gsm.chwijuntime.model.CorrectionApply;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CorrectionApprovalSaveDto {

    private String classNumber;
    private String correctionContent;

    public Correction toEntityByApproval(CorrectionApply correctionApply){
        return Correction.builder()
                .correctionContent(this.correctionContent)
                .correctionApply(correctionApply)
                .classNumber(this.classNumber)
                .build();
    }
}
