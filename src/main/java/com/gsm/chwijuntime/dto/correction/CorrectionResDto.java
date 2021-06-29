package com.gsm.chwijuntime.dto.correction;

import com.gsm.chwijuntime.model.CorrectionStatus;
import com.gsm.chwijuntime.model.CorrectionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CorrectionResDto {

    private Long correctionIdx;
    private String classNumber;
    private String correctionContent;
    private String reasonForRejection;

    private Long correctionApplyIdx;
    private CorrectionStatus correctionStatus;
    private CorrectionType correctionType;

    private Long memberPortfolioIdx;
    private String notionPortfolioURL;

    private Long memberResumeIdx;
    private String resumeFileURL;

    private String memberEmail;
    private String memberClassNumber;

}
