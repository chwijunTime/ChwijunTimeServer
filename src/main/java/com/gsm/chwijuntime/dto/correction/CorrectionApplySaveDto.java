package com.gsm.chwijuntime.dto.correction;

import com.gsm.chwijuntime.model.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CorrectionApplySaveDto {

    private String classNumber;
    private String name;

    public CorrectionApply toEntityByCorrectionApplyMemberPortfolio(CorrectionType correctionType, MemberPortfolio memberPortfolio, Member member){
        return CorrectionApply.builder()
                .correctionStatus(CorrectionStatus.Correction_Applying)
                .memberPortfolio(memberPortfolio)
                .Name(this.name)
                .member(member)
                .classNumber(this.classNumber)
                .correctionType(correctionType)
                .build();
    }

    public CorrectionApply toEntityByCorrectionApplyMemberMemberResume(CorrectionType correctionType, MemberResume memberResume, Member member){
        return CorrectionApply.builder()
                .correctionStatus(CorrectionStatus.Correction_Applying)
                .memberResume(memberResume)
                .Name(this.name)
                .classNumber(this.classNumber)
                .correctionType(correctionType)
                .member(member)
                .build();
    }
}
