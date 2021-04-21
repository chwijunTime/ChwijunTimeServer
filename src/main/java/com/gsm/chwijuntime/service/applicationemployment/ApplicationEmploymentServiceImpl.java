package com.gsm.chwijuntime.service.applicationemployment;

import com.gsm.chwijuntime.dto.applicationemployment.ApplicationEmploymentSaveDto;
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
        Member findMember = memberRepository.findByMemberEmail(getUserEmailUtil.GetUserEmail()).orElseThrow(null);
        applicationEmploymentRepository.save(applicationemploymentSaveDto.toEntityByApplicationEmployment(findMember, findMyEmploymentAnnouncement));
    }

    @Override
    public ApplicationEmployment findAllApplication() {
        List<ApplicationEmployment> all = applicationEmploymentRepository.findAll();
        for (ApplicationEmployment applicationEmployment : all) {
            System.out.println("============== applicationEmployment.getApplicationEmploymentPortfolioURL() = " + applicationEmployment.getApplicationEmploymentPortfolioURL());
            Member member = applicationEmployment.getMember();
            System.out.println("member = " + member.getMemberEmail());
            EmploymentAnnouncement employmentAnnouncement = applicationEmployment.getEmploymentAnnouncement();
            System.out.println("employmentAnnouncement.getEmploymentAnnouncementName() = " + employmentAnnouncement.getEmploymentAnnouncementName());
        }
        return null;
    }

    @Override
    public void AcceptApplication() {

    }

    @Override
    public void RejectApplication() {

    }

    @Override
    public ApplicationEmployment ApplicationDetail() {
        return null;
    }

    @Override
    public ApplicationEmployment findByAccept() {
        return null;
    }

    @Override
    public ApplicationEmployment findByReject() {
        return null;
    }
}
