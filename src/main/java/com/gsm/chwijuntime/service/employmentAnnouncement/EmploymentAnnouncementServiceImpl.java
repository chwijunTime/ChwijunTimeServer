package com.gsm.chwijuntime.service.employmentAnnouncement;

import com.gsm.chwijuntime.advice.exception.AuthorNotCertifiedException;
import com.gsm.chwijuntime.advice.exception.CAuthenticationEntryPointException;
import com.gsm.chwijuntime.dto.employmentAnnouncement.EmploymentAnnouncementResponseDto;
import com.gsm.chwijuntime.dto.employmentAnnouncement.EmploymentAnnouncementSaveDto;
import com.gsm.chwijuntime.dto.employmentAnnouncement.EmploymentAnnouncementUpdateDto;
import com.gsm.chwijuntime.model.EmploymentAnnouncement;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.repository.EmploymentAnnouncementRepository;
import com.gsm.chwijuntime.repository.MemberRepository;
import com.gsm.chwijuntime.util.GetUserEmailUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class EmploymentAnnouncementServiceImpl implements EmploymentAnnouncementService {

    private final ModelMapper mapper;
    private final MemberRepository memberRepository;
    private final GetUserEmailUtil getUserEmailUtil;
    private final EmploymentAnnouncementRepository employmentAnnouncementRepository;

    @Transactional
    @Override
    public void EmploymentAnnouncementSave(EmploymentAnnouncementSaveDto employmentAnnouncementSaveDto) {
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.GetUserEmail()).orElseThrow();
        employmentAnnouncementRepository.save(employmentAnnouncementSaveDto.toEntityByEmploymentAnnouncement(member));
    }

    @Override
    public EmploymentAnnouncementResponseDto findByOne(Long idx) {
        EmploymentAnnouncementResponseDto employmentAnnouncementResponseDto = employmentAnnouncementRepository.findById(idx)
                .map(m -> mapper.map(m, EmploymentAnnouncementResponseDto.class)).orElseThrow(null);
        return employmentAnnouncementResponseDto;
    }

    @Override
    public List<EmploymentAnnouncementResponseDto> findByAll() {
        List<EmploymentAnnouncementResponseDto> responseDtoList = new ArrayList<>();
        List<EmploymentAnnouncementResponseDto> employmentAnnouncementResponseDtos = employmentAnnouncementRepository.findAll().stream()
                .map(m -> mapper.map(m, EmploymentAnnouncementResponseDto.class))
                .collect(Collectors.toList());
        LocalDate now = LocalDate.now();
        //지난 날짜 걸러주기
        for (EmploymentAnnouncementResponseDto employmentAnnouncementResponseDto : employmentAnnouncementResponseDtos) {
            int compare = employmentAnnouncementResponseDto.getDeadLine().compareTo(now);
            if(compare >= 0) {
                System.out.println("========================== deadLine 안 지남");
                responseDtoList.add(employmentAnnouncementResponseDto);
            }
        }
        return responseDtoList;
    }

    @Transactional
    @Override
    public void updateEmploymentAnnouncement(Long idx, EmploymentAnnouncementUpdateDto employmentAnnouncementUpdateDto) {
        EmploymentAnnouncement employmentAnnouncement = employmentAnnouncementRepository.findById(idx).orElseThrow(null);
        UserWriteCheck(employmentAnnouncement);
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
