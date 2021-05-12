package com.gsm.chwijuntime.service.correction;

import com.gsm.chwijuntime.advice.exception.CAuthenticationEntryPointException;
import com.gsm.chwijuntime.advice.exception.NotFoundPortfolioException;
import com.gsm.chwijuntime.advice.exception.NotFoundResumeException;
import com.gsm.chwijuntime.dto.correction.CorrectionApplySaveDto;
import com.gsm.chwijuntime.dto.correction.CorrectionApprovalSaveDto;
import com.gsm.chwijuntime.dto.correction.CorrectionRejectionSaveDto;
import com.gsm.chwijuntime.model.*;
import com.gsm.chwijuntime.repository.*;
import com.gsm.chwijuntime.util.GetUserEmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CorrectionServiceImpl implements CorrectionService {

    private final MemberPortfolioRepository memberPortfolioRepository;
    private final MemberResumeRepository memberResumeRepository;
    private final MemberRepository memberRepository;
    private final GetUserEmailUtil getUserEmailUtil;
    private final CorrectionApplyRepository correctionApplyRepository;
    private final CorrectionRepository correctionRepository;

    @Override
    public void saveCorrectionApply(Long idx, CorrectionApplySaveDto correctionApplySaveDto, CorrectionType correctionType) throws Exception {
        checkCorrectionApply(idx);
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        if (correctionType.equals(CorrectionType.Resume)) {
            MemberResume memberResume = memberResumeRepository.findById(idx).orElseThrow(NotFoundResumeException::new);
            correctionApplyRepository.save(correctionApplySaveDto.toEntityByCorrectionApplyMemberMemberResume(correctionType, memberResume, member));
        } else {
            MemberPortfolio memberPortfolio = memberPortfolioRepository.findById(idx).orElseThrow(NotFoundPortfolioException::new);
            correctionApplyRepository.save(correctionApplySaveDto.toEntityByCorrectionApplyMemberPortfolio(correctionType, memberPortfolio, member));
        }
    }

    private void checkCorrectionApply(Long idx) throws Exception {
        Optional<CorrectionApply> byId = correctionApplyRepository.findById(idx);
        if (byId.isEmpty()){
            return;
        } else {
            if(byId.get().getCorrectionStatus().equals(CorrectionStatus.Correction_Applying)){
                throw new Exception("이미 신청 중입니다.");
            } else if(byId.get().getCorrectionStatus().equals(CorrectionStatus.Correction_Rejection)) {
                throw new Exception("이미 거절 되었습니다.");
            } else if(byId.get().getCorrectionStatus().equals(CorrectionStatus.Correction_Successful)) {
                throw new Exception("이미 첨삭 되었습니다.");
            }
        }
    }

    // 관리자 요청 승인 & 첨삭
    @Transactional
    @Override
    public void requestApproval(Long idx, CorrectionApprovalSaveDto correctionApprovalSaveDto) throws Exception {
        checkAdmin(idx);
        // 요청 검색
        CorrectionApply correctionApply = correctionApplyRepository.findById(idx).orElseThrow(null);
        // 상태 변경
        correctionApply.changeApproval();
        // 첨삭 저장
        correctionRepository.save(correctionApprovalSaveDto.toEntityByApproval(correctionApply));
    }

    //관리자 요청 거절 & 이유
    @Transactional
    @Override
    public void requestRejection(Long idx, CorrectionRejectionSaveDto correctionRejectionSaveDto) throws Exception {
        checkAdmin(idx);
        CorrectionApply correctionApply = correctionApplyRepository.findById(idx).orElseThrow(null);
        // 상태 바꿈
        correctionApply.changeRejection();
        //저장
        correctionRepository.save(correctionRejectionSaveDto.toEntityByApproval(correctionApply));
    }

    private void checkAdmin(Long idx) throws Exception {
        Optional<CorrectionApply> byId = correctionApplyRepository.findById(idx);
        if (byId.isEmpty()){
            return;
        } else {
            if(byId.get().getCorrectionStatus().equals(CorrectionStatus.Correction_Rejection)) {
                throw new Exception("이미 거절 되었습니다.");
            } else if(byId.get().getCorrectionStatus().equals(CorrectionStatus.Correction_Successful)) {
                throw new Exception("이미 첨삭 되었습니다.");
            }
        }
    }

    // 관리자가 신청 단일 조회하기
    @Override
    public CorrectionApply findByIdx(Long idx) {
        return null;
    }

    // 관리자가 신청한 모든 사람 보기
    @Override
    public List<CorrectionApply> findAll() {
        return null;
    }
}
