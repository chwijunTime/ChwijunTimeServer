package com.gsm.chwijuntime.service.correction;

import com.gsm.chwijuntime.dto.correction.CorrectionApprovalSaveDto;
import com.gsm.chwijuntime.dto.correction.CorrectionRejectionSaveDto;
import com.gsm.chwijuntime.model.Correction;
import com.gsm.chwijuntime.model.CorrectionApply;
import com.gsm.chwijuntime.model.CorrectionType;

import java.util.List;

public interface CorrectionService {
    void saveCorrectionApply(Long idx, CorrectionType correctionType) throws Exception;
    CorrectionApply findByIdx(Long idx);
    List<CorrectionApply> findAll();

    void requestApproval(Long idx, CorrectionApprovalSaveDto correctionApprovalSaveDto) throws Exception;
    void requestRejection(Long idx, CorrectionRejectionSaveDto correctionRejectionSaveDto) throws Exception;

    List<CorrectionApply> findByMyApply();
    List<Correction> findMyCorrection();
}
