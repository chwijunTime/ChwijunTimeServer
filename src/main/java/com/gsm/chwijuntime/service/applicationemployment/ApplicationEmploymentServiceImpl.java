package com.gsm.chwijuntime.service.applicationemployment;

import com.gsm.chwijuntime.advice.exception.*;
import com.gsm.chwijuntime.dto.applicationemployment.ApplicationEmploymentSaveDto;
import com.gsm.chwijuntime.dto.applicationemployment.FindAllApplicationDetailResDto;
import com.gsm.chwijuntime.dto.applicationemployment.FindAllApplicationResDto;
import com.gsm.chwijuntime.model.*;
import com.gsm.chwijuntime.repository.ApplicationEmploymentRepository;
import com.gsm.chwijuntime.repository.EmploymentAnnouncementRepository;
import com.gsm.chwijuntime.repository.MemberRepository;
import com.gsm.chwijuntime.util.GetUserEmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicationEmploymentServiceImpl implements ApplicationEmploymentService {

    private final MemberRepository memberRepository;
    private final GetUserEmailUtil getUserEmailUtil;
    private final ApplicationEmploymentRepository applicationEmploymentRepository;
    private final EmploymentAnnouncementRepository employmentAnnouncementRepository;

    @Transactional
    @Override
    public void application(Long employmentAnnouncementIdx, ApplicationEmploymentSaveDto applicationemploymentSaveDto) {
        EmploymentAnnouncement findMyEmploymentAnnouncement = employmentAnnouncementRepository.findById(employmentAnnouncementIdx).orElseThrow(NotFoundEmploymentAnnouncementException::new);
        // 신청 여부 판별
        Optional<ApplicationEmployment> byEmploymentAnnouncement = applicationEmploymentRepository.findByEmploymentAnnouncement(findMyEmploymentAnnouncement);
        if (byEmploymentAnnouncement.isEmpty()) {
            //공고 날짜/신청 날짜 비교
            int compare = applicationemploymentSaveDto.getLocalDate().compareTo(findMyEmploymentAnnouncement.getDeadLine());
            compareToDate(compare);
            Member findMember = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
            applicationEmploymentRepository.save(applicationemploymentSaveDto.toEntityByApplicationEmployment(findMember, findMyEmploymentAnnouncement));
        } else {
            throw new RedundantApplicationException();
        }
    }

    @Override
    public List<FindAllApplicationResDto> findByMember() {
        List<FindAllApplicationResDto> findAllApplicationResDtos = new ArrayList<>();
        Member findMember = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        List<ApplicationEmployment> applicationEmploymentStatus = applicationEmploymentRepository.findByMember(findMember);
        return getFindAllApplicationResDtos(findAllApplicationResDtos, applicationEmploymentStatus);
    }

    private void compareToDate(int compare) {
        if(compare >= 0) {
            throw new ApplicationDateExpirationException();
        }
    }

    @Override
    public FindAllApplicationDetailResDto applicationDetail(Long idx) {
        ApplicationEmployment applicationEmployment = applicationEmploymentRepository.findByApplicationEmploymentIdx(idx);
        return FindAllApplicationDetailResDto.builder()
                .applicationEmployment(applicationEmployment)
                .build();
    }

    @Override
    public List<FindAllApplicationResDto> findByStatus(ApplicationEmploymentStatus status) {
        List<FindAllApplicationResDto> findAllApplicationResDtos = new ArrayList<>();
        List<ApplicationEmployment> applicationEmploymentStatus = new ArrayList<>();
        if (status.equals(ApplicationEmploymentStatus.Approve)){
            applicationEmploymentStatus = applicationEmploymentRepository.findByApplicationEmploymentStatus(ApplicationEmploymentStatus.Approve);
        } else if(status.equals(ApplicationEmploymentStatus.Wait)) {
            applicationEmploymentStatus = applicationEmploymentRepository.findByApplicationEmploymentStatus(ApplicationEmploymentStatus.Wait);
        } else if(status.equals(ApplicationEmploymentStatus.Reject)) {
            applicationEmploymentStatus = applicationEmploymentRepository.findByApplicationEmploymentStatus(ApplicationEmploymentStatus.Reject);
        } else if(status.equals(ApplicationEmploymentStatus.All)){
            applicationEmploymentStatus = applicationEmploymentRepository.findAll();
        }

        return getFindAllApplicationResDtos(findAllApplicationResDtos, applicationEmploymentStatus);
    }

    private List<FindAllApplicationResDto> getFindAllApplicationResDtos(List<FindAllApplicationResDto> findAllApplicationResDtos, List<ApplicationEmployment> applicationEmploymentStatus) {
        for (ApplicationEmployment applicationEmployment : applicationEmploymentStatus) {
            Member member = applicationEmployment.getMember();
            EmploymentAnnouncement employmentAnnouncement = applicationEmployment.getEmploymentAnnouncement();
            FindAllApplicationResDto build = FindAllApplicationResDto.builder()
                    .applicationEmploymentIdx(applicationEmployment.getApplicationEmploymentIdx())
                    .applicationEmploymentStatus(applicationEmployment.getApplicationEmploymentStatus())
                    .memberClassNumber(member.getMemberClassNumber())
                    .gitHubURL(applicationEmployment.getGitHubURL())
                    .employmentAnnouncementName(employmentAnnouncement.getEmploymentAnnouncementName())
                    .recruitmentField(employmentAnnouncement.getRecruitmentField())
                    .build();
            findAllApplicationResDtos.add(build);
        }
        return findAllApplicationResDtos;
    }

    @Transactional
    @Override
    public void acceptApplication(Long idx) {
        ApplicationEmployment applicationEmployment = applicationEmploymentRepository.findById(idx).orElseThrow(NotFoundApplicationEmploymentException::new);
        if(applicationEmployment.getApplicationEmploymentStatus().equals(ApplicationEmploymentStatus.Approve)){
            throw new RequestAlreadyApprovedException("이미 승인된 요청입니다.");
        } else if(applicationEmployment.getApplicationEmploymentStatus().equals(ApplicationEmploymentStatus.Reject)){
            throw new RequestAlreadyRejectedException("이미 거절된 요청입니다.");
        }
        applicationEmployment.changeApplicationEmploymentStatusApprove();
    }

    @Transactional
    @Override
    public void rejectApplication(Long idx) {
        ApplicationEmployment applicationEmployment = applicationEmploymentRepository.findById(idx).orElseThrow(NotFoundApplicationEmploymentException::new);
        if(applicationEmployment.getApplicationEmploymentStatus().equals(ApplicationEmploymentStatus.Approve)){
            throw new RequestAlreadyApprovedException("이미 승인된 요청입니다.");
        } else if(applicationEmployment.getApplicationEmploymentStatus().equals(ApplicationEmploymentStatus.Reject)){
            throw new RequestAlreadyRejectedException("이미 거절된 요청입니다.");
        }
        applicationEmployment.changeApplicationEmploymentStatusApproveReject();
    }
}
