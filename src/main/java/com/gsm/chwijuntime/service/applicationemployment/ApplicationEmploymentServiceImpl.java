package com.gsm.chwijuntime.service.applicationemployment;

import com.gsm.chwijuntime.advice.exception.CAuthenticationEntryPointException;
import com.gsm.chwijuntime.dto.applicationemployment.ApplicationEmploymentSaveDto;
import com.gsm.chwijuntime.dto.applicationemployment.FindAllApplicationDetailResDto;
import com.gsm.chwijuntime.dto.applicationemployment.FindAllApplicationResDto;
import com.gsm.chwijuntime.model.ApplicationEmployment;
import com.gsm.chwijuntime.model.EmploymentAnnouncement;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.repository.ApplicationEmploymentRepository;
import com.gsm.chwijuntime.repository.EmploymentAnnouncementRepository;
import com.gsm.chwijuntime.repository.MemberRepository;
import com.gsm.chwijuntime.util.GetUserEmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
        EmploymentAnnouncement findMyEmploymentAnnouncement = employmentAnnouncementRepository.findById(employmentAnnouncementIdx).orElseThrow(null);
        Member findMember = memberRepository.findByMemberEmail(getUserEmailUtil.GetUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        applicationEmploymentRepository.save(applicationemploymentSaveDto.toEntityByApplicationEmployment(findMember, findMyEmploymentAnnouncement));
    }

    @Override
    public List<FindAllApplicationResDto> findAllApplication() {
        List<FindAllApplicationResDto> findAllApplicationResDtos = new ArrayList<>();
        List<ApplicationEmployment> all = applicationEmploymentRepository.findAll();
        for (ApplicationEmployment applicationEmployment : all) {
            Member member = applicationEmployment.getMember();
            EmploymentAnnouncement employmentAnnouncement = applicationEmployment.getEmploymentAnnouncement();
            FindAllApplicationResDto build = FindAllApplicationResDto.builder()
                    .memberClassNumber(member.getMemberClassNumber())
                    .gitHubURL(applicationEmployment.getGitHubURL())
                    .employmentAnnouncementName(employmentAnnouncement.getEmploymentAnnouncementName())
                    .recruitmentField(employmentAnnouncement.getRecruitmentField())
                    .build();
            findAllApplicationResDtos.add(build);
        }
        return findAllApplicationResDtos;
    }

    @Override
    public FindAllApplicationDetailResDto applicationDetail(Long idx) {
        ApplicationEmployment applicationEmployment = applicationEmploymentRepository.findByApplicationEmploymentIdx(idx);
        Member member = applicationEmployment.getMember();
        EmploymentAnnouncement employmentAnnouncement = applicationEmployment.getEmploymentAnnouncement();

        return FindAllApplicationDetailResDto.builder()
                .member(member)
                .applicationEmployment(applicationEmployment)
                .employmentAnnouncement(employmentAnnouncement)
                .build();
    }

    @Override
    public ApplicationEmployment findByAccept() {
        return null;
    }

    @Override
    public ApplicationEmployment findByReject() {
        return null;
    }

    @Override
    public void AcceptApplication() {

    }

    @Override
    public void RejectApplication() {

    }
}
