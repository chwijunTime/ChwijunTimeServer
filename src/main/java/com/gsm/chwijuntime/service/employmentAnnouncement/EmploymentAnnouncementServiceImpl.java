package com.gsm.chwijuntime.service.employmentAnnouncement;

import com.gsm.chwijuntime.advice.exception.AuthorNotCertifiedException;
import com.gsm.chwijuntime.advice.exception.CAuthenticationEntryPointException;
import com.gsm.chwijuntime.dto.employmentAnnouncement.EmploymentAnnouncementSaveDto;
import com.gsm.chwijuntime.dto.employmentAnnouncement.EmploymentAnnouncementUpdateDto;
import com.gsm.chwijuntime.model.EmploymentAnnouncement;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.repository.EmploymentAnnouncementRepository;
import com.gsm.chwijuntime.repository.MemberRepository;
import com.gsm.chwijuntime.util.GetUserEmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class EmploymentAnnouncementServiceImpl implements EmploymentAnnouncementService {

    private final MemberRepository memberRepository;
    private final GetUserEmailUtil getUserEmailUtil;
    private final EmploymentAnnouncementRepository employmentAnnouncementRepository;

    @Override
    public void EmploymentAnnouncementSave(EmploymentAnnouncementSaveDto employmentAnnouncementSaveDto) {
        employmentAnnouncementRepository.save(employmentAnnouncementSaveDto.toEntityByEmploymentAnnouncement());
    }

    @Override
    public EmploymentAnnouncement findByOne(Long idx) {
        return employmentAnnouncementRepository.findById(idx).orElseThrow(null);
    }

    @Override
    public List<EmploymentAnnouncement> findByAll() {
        return employmentAnnouncementRepository.findAll();
    }

    @Transactional
    @Override
    public void updateEmploymentAnnouncement(Long idx, EmploymentAnnouncementUpdateDto employmentAnnouncementUpdateDto) {
        EmploymentAnnouncement employmentAnnouncement = employmentAnnouncementRepository.findById(idx).orElseThrow(null);
        employmentAnnouncement.update(employmentAnnouncementUpdateDto);
    }

    @Transactional
    @Override
    public void deleteEmploymentAnnouncement(Long idx) {
        EmploymentAnnouncement employmentAnnouncement = employmentAnnouncementRepository.findById(idx).orElseThrow(null);
        UserWriteCheck(employmentAnnouncement);
        employmentAnnouncementRepository.deleteById(idx);
    }

    //작성자 권한 체크
    public void UserWriteCheck(EmploymentAnnouncement employmentAnnouncement) {
        Member CurrentUser = memberRepository.findByMemberEmail(getUserEmailUtil.GetUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        Member WriteMember = employmentAnnouncementRepository.findById(employmentAnnouncement.getEmploymentAnnouncementIdx()).orElseThrow(CAuthenticationEntryPointException::new).getMember();
        if (!CurrentUser.getMemberEmail().equals(WriteMember.getMemberEmail())) {
            throw new AuthorNotCertifiedException();
        }
    }
}
