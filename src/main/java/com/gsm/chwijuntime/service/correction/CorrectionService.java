package com.gsm.chwijuntime.service.correction;

import com.gsm.chwijuntime.dto.correction.CorrectionApplySaveDto;
import com.gsm.chwijuntime.dto.correction.CorrectionApprovalSaveDto;
import com.gsm.chwijuntime.dto.correction.CorrectionRejectionSaveDto;
import com.gsm.chwijuntime.model.CorrectionApply;
import com.gsm.chwijuntime.model.CorrectionType;

import java.util.List;

public interface CorrectionService {

    void saveCorrectionApply(Long idx, CorrectionApplySaveDto correctionApplySaveDto, CorrectionType correctionType) throws Exception;
    CorrectionApply findByIdx(Long idx);
    List<CorrectionApply> findAll();

    void requestApproval(Long idx, CorrectionApprovalSaveDto correctionApprovalSaveDto) throws Exception;
    void requestRejection(Long idx, CorrectionRejectionSaveDto correctionRejectionSaveDto) throws Exception;

}
