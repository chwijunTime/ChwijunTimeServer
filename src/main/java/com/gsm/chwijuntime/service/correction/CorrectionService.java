package com.gsm.chwijuntime.service.correction;

import com.gsm.chwijuntime.dto.correction.CorrectionApplyResDto;
import com.gsm.chwijuntime.dto.correction.CorrectionApprovalSaveDto;
import com.gsm.chwijuntime.dto.correction.CorrectionRejectionSaveDto;
import com.gsm.chwijuntime.dto.correction.CorrectionResDto;
import com.gsm.chwijuntime.model.CorrectionApply;
import com.gsm.chwijuntime.model.CorrectionType;

import java.util.List;

public interface CorrectionService {
    void saveCorrectionApply(Long idx, CorrectionType correctionType) throws Exception;
    CorrectionApply findByIdx(Long idx);
    List<CorrectionApplyResDto> findAll();

    void requestApproval(Long idx, CorrectionApprovalSaveDto correctionApprovalSaveDto) throws Exception;
    void requestRejection(Long idx, CorrectionRejectionSaveDto correctionRejectionSaveDto) throws Exception;

    List<CorrectionApplyResDto> findByMyApply();
    List<CorrectionResDto> findMyCorrection();
}
