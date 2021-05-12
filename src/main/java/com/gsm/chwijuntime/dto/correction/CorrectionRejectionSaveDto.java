package com.gsm.chwijuntime.dto.correction;

import com.gsm.chwijuntime.model.Correction;
import com.gsm.chwijuntime.model.CorrectionApply;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CorrectionRejectionSaveDto {

    private String reasonForRejection;

    public Correction toEntityByApproval(CorrectionApply correctionApply){
        return Correction.builder()
                .correctionApply(correctionApply)
                .reasonForRejection(this.reasonForRejection)
                .build();
    }

}
