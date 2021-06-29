package com.gsm.chwijuntime.service.correction;

import com.gsm.chwijuntime.advice.exception.*;
import com.gsm.chwijuntime.dto.correction.*;
import com.gsm.chwijuntime.model.*;
import com.gsm.chwijuntime.repository.*;
import com.gsm.chwijuntime.util.GetUserEmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public List<CorrectionApplyResDto> findByMyApply() {
        List<CorrectionApplyResDto> correctionApplyResDtos = new ArrayList<>();
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        List<CorrectionApply> byMember = correctionApplyRepository.findByMember(member);
        for (CorrectionApply correctionApply : byMember) {
            if(correctionApply.getCorrectionType().equals(CorrectionType.Portfolio)){
                CorrectionApplyResDto correctionApplyResDto = mappingPortfolioRes(correctionApply, correctionApply.getMemberPortfolio(), correctionApply.getMember());
                correctionApplyResDtos.add(correctionApplyResDto);
            } else {   // 이력서 이면
                CorrectionApplyResDto correctionApplyResDto = mappingMemberResumeRes(correctionApply, correctionApply.getMemberResume(), correctionApply.getMember());
                correctionApplyResDtos.add(correctionApplyResDto);
            }
        }
        return correctionApplyResDtos;
    }

    private CorrectionApplyResDto mappingPortfolioRes(CorrectionApply correctionApply, MemberPortfolio memberPortfolio, Member member){
            CorrectionApplyResDto correctionApplyResDto = CorrectionApplyResDto.builder()
                    .correctionApplyIdx(correctionApply.getCorrectionApplyIdx())
                    .correctionStatus(correctionApply.getCorrectionStatus())
                    .correctionType(correctionApply.getCorrectionType())
                    .memberPortfolioIdx(memberPortfolio.getMemberPortfolioIdx())
                    .notionPortfolioURL(memberPortfolio.getNotionPortfolioURL())
                    .memberEmail(member.getMemberEmail())
                    .memberClassNumber(member.getMemberClassNumber())
                    .build();
            return correctionApplyResDto;
    }

    private CorrectionApplyResDto mappingMemberResumeRes(CorrectionApply correctionApply, MemberResume memberResume, Member member){
            CorrectionApplyResDto correctionApplyResDto = CorrectionApplyResDto.builder()
                    .correctionApplyIdx(correctionApply.getCorrectionApplyIdx())
                    .correctionStatus(correctionApply.getCorrectionStatus())
                    .correctionType(correctionApply.getCorrectionType())
                    .memberResumeIdx(memberResume.getMemberResumeIdx())
                    .notionPortfolioURL(memberResume.getResumeFileURL())
                    .memberEmail(member.getMemberEmail())
                    .memberClassNumber(member.getMemberClassNumber())
                    .build();
            return correctionApplyResDto;
    }


    @Override
    public List<CorrectionResDto> findMyCorrection() {
        List<CorrectionResDto> correctionResDtos = new ArrayList<>();
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(CAuthenticationEntryPointException::new); // 현재 사용자
        String classNumber = member.getMemberClassNumber();
        List<Correction> byClassNumber = correctionRepository.findByClassNumber(classNumber);
        for (Correction correction : byClassNumber) {
            if (correction.getCorrectionApply().getCorrectionType().equals(CorrectionType.Portfolio)){
                CorrectionResDto correctionResDto = mappingCorrectionPortfolio(correction, correction.getCorrectionApply(), correction.getCorrectionApply().getMemberPortfolio(), member);
                correctionResDtos.add(correctionResDto);
            } else {   //이력서 이면
                CorrectionResDto correctionResDto = mappingCorrectionMemberResume(correction, correction.getCorrectionApply(), correction.getCorrectionApply().getMemberResume(), member);
                correctionResDtos.add(correctionResDto);
            }
        }
        return correctionResDtos;
    }

    private CorrectionResDto mappingCorrectionPortfolio(Correction correction, CorrectionApply correctionApply, MemberPortfolio memberPortfolio, Member member){
        CorrectionResDto correctionResDto = CorrectionResDto.builder()
                .correctionIdx(correction.getCorrectionIdx())
                .classNumber(correction.getClassNumber())
                .correctionContent(correction.getCorrectionContent())
                .reasonForRejection(correction.getReasonForRejection())
                .correctionApplyIdx(correctionApply.getCorrectionApplyIdx())
                .correctionStatus(correctionApply.getCorrectionStatus())
                .correctionType(correctionApply.getCorrectionType())
                .memberPortfolioIdx(memberPortfolio.getMemberPortfolioIdx())
                .notionPortfolioURL(memberPortfolio.getNotionPortfolioURL())
                .memberEmail(member.getMemberEmail())
                .memberClassNumber(member.getMemberClassNumber())
                .build();
        return correctionResDto;
    }

    private CorrectionResDto mappingCorrectionMemberResume(Correction correction, CorrectionApply correctionApply, MemberResume memberResume, Member member){
        CorrectionResDto correctionResDto = CorrectionResDto.builder()
                .correctionIdx(correction.getCorrectionIdx())
                .classNumber(correction.getClassNumber())
                .correctionContent(correction.getCorrectionContent())
                .reasonForRejection(correction.getReasonForRejection())
                .correctionApplyIdx(correctionApply.getCorrectionApplyIdx())
                .correctionStatus(correctionApply.getCorrectionStatus())
                .correctionType(correctionApply.getCorrectionType())
                .memberResumeIdx(memberResume.getMemberResumeIdx())
                .resumeFileURL(memberResume.getResumeFileURL())
                .memberEmail(member.getMemberEmail())
                .memberClassNumber(member.getMemberClassNumber())
                .build();
        return correctionResDto;
    }


    private void checkAdmin(Long idx) {
        Optional<CorrectionApply> byId = correctionApplyRepository.findById(idx);
        if (byId.isEmpty()){
            return;
        } else {
            if(byId.get().getCorrectionStatus().equals(CorrectionStatus.Correction_Rejection)) {
                throw new RequestAlreadyRejectedException();
            } else if(byId.get().getCorrectionStatus().equals(CorrectionStatus.Correction_Successful)) {
                throw new RequestAlreadyApprovedException();
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
