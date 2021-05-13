package com.gsm.chwijuntime.dto.correction;

import com.gsm.chwijuntime.model.*;
import lombok.*;
import org.springframework.stereotype.Component;


@Component
public class CorrectionApplySaveDto {

    public CorrectionApply toEntityByCorrectionApplyMemberPortfolio(CorrectionType correctionType, MemberPortfolio memberPortfolio, Member member){
        return CorrectionApply.builder()
                .correctionStatus(CorrectionStatus.Correction_Applying)
                .memberPortfolio(memberPortfolio)
                .member(member)
                .correctionType(correctionType)
                .build();
    }

    public CorrectionApply toEntityByCorrectionApplyMemberMemberResume(CorrectionType correctionType, MemberResume memberResume, Member member){
        return CorrectionApply.builder()
                .correctionStatus(CorrectionStatus.Correction_Applying)
                .memberResume(memberResume)
                .correctionType(correctionType)
                .member(member)
                .build();
    }
}
