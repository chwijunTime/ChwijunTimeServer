package com.gsm.chwijuntime.dto.correction;

import com.gsm.chwijuntime.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CorrectionApplyResDto {

    //CorrectionApply
    private Long correctionApplyIdx;
    private CorrectionStatus correctionStatus;
    private CorrectionType correctionType;

    //MemberPortfolio
    private Long memberResumeIdx;
    private String resumeFileURL;

    //MemberPortfolio
    private Long memberPortfolioIdx;
    private String notionPortfolioURL;

    //Member
    private String memberEmail;
    private String memberClassNumber;



    public void mappingResume(CorrectionApply correctionApply, MemberResume memberResume, MemberPortfolio memberPortfolio ,Member member){
        this.correctionApplyIdx = correctionApply.getCorrectionApplyIdx();
        this.correctionStatus = correctionApply.getCorrectionStatus();
        this.correctionType = correctionApply.getCorrectionType();
        if(memberPortfolio == null){
            this.memberResumeIdx = memberResume.getMemberResumeIdx();
            this.resumeFileURL = memberResume.getResumeFileURL();
        } else {
            this.memberPortfolioIdx = memberPortfolio.getMemberPortfolioIdx();
            this.notionPortfolioURL = memberPortfolio.getNotionPortfolioURL();
        }
        this.memberEmail = member.getMemberEmail();
        this.memberClassNumber = member.getMemberClassNumber();
    }
}
