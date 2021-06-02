package com.gsm.chwijuntime.service.correction;

import com.gsm.chwijuntime.advice.exception.CAuthenticationEntryPointException;
import com.gsm.chwijuntime.advice.exception.NotFoundCorrectionApply;
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
    private final CorrectionApplySaveDto correctionApplySaveDto;

    @Transactional
    @Override
    public void saveCorrectionApply(Long idx, CorrectionType correctionType) throws Exception {
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        if (correctionType.equals(CorrectionType.Resume)) {
            MemberResume memberResume = memberResumeRepository.findById(idx).orElseThrow(NotFoundResumeException::new);
            correctionApplyRepository.save(correctionApplySaveDto.toEntityByCorrectionApplyMemberMemberResume(correctionType, memberResume, member));
        } else {
            MemberPortfolio memberPortfolio = memberPortfolioRepository.findById(idx).orElseThrow(NotFoundPortfolioException::new);
            correctionApplyRepository.save(correctionApplySaveDto.toEntityByCorrectionApplyMemberPortfolio(correctionType, memberPortfolio, member));
        }
    }

    // 관리자 요청 승인 & 첨삭
    @Transactional
    @Override
    public void requestApproval(Long idx, CorrectionApprovalSaveDto correctionApprovalSaveDto) throws Exception {
        checkAdmin(idx);
        CorrectionApply correctionApply = correctionApplyRepository.findById(idx).orElseThrow(NotFoundCorrectionApply::new);
        correctionApply.changeApproval();
        correctionRepository.save(correctionApprovalSaveDto.toEntityByApproval(correctionApply));
    }

    //관리자 요청 거절 & 이유
    @Transactional
    @Override
    public void requestRejection(Long idx, CorrectionRejectionSaveDto correctionRejectionSaveDto) throws Exception {
        checkAdmin(idx);
        CorrectionApply correctionApply = correctionApplyRepository.findById(idx).orElseThrow(NotFoundCorrectionApply::new);
        correctionApply.changeRejection();
        correctionRepository.save(correctionRejectionSaveDto.toEntityByApproval(correctionApply));
    }

    // 내가 신청한 리스트 보기
    @Override
    public List<CorrectionApply> findByMyApply() {
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        List<CorrectionApply> byMember = correctionApplyRepository.findByMember(member);
        return byMember;
    }

    @Override
    public List<Correction> findMyCorrection() {
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(CAuthenticationEntryPointException::new); // 현재 사용자
        String classNumber = member.getMemberClassNumber();
        List<Correction> byClassNumber = correctionRepository.findByClassNumber(classNumber);
        return byClassNumber;
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
        return correctionApplyRepository.findById(idx).orElseThrow(NotFoundCorrectionApply::new);
    }

    // 관리자가 신청한 모든 사람 보기
    @Override
    public List<CorrectionApply> findAll() {
        List<CorrectionApply> all = correctionApplyRepository.findAll();
        return all;
    }
}
