package com.gsm.chwijuntime.service.employmentAnnouncement;

import com.gsm.chwijuntime.advice.exception.AuthorNotCertifiedException;
import com.gsm.chwijuntime.advice.exception.CAuthenticationEntryPointException;
import com.gsm.chwijuntime.advice.exception.NotFoundEmploymentAnnouncementException;
import com.gsm.chwijuntime.advice.exception.NotFoundTagException;
import com.gsm.chwijuntime.dto.employmentAnnouncement.EmploymentAnnouncementResponseDto;
import com.gsm.chwijuntime.dto.employmentAnnouncement.EmploymentAnnouncementSaveDto;
import com.gsm.chwijuntime.dto.employmentAnnouncement.EmploymentAnnouncementUpdateDto;
import com.gsm.chwijuntime.model.EmploymentAnnouncement;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.model.tagmapping.EmploymentAnnouncementTag;
import com.gsm.chwijuntime.repository.EmploymentAnnouncementRepository;
import com.gsm.chwijuntime.repository.MemberRepository;
import com.gsm.chwijuntime.repository.TagRepository;
import com.gsm.chwijuntime.repository.tag.EmploymentAnnouncementTagRepository;
import com.gsm.chwijuntime.util.GetUserEmailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
@Service
public class EmploymentAnnouncementServiceImpl implements EmploymentAnnouncementService {

    private final ModelMapper mapper;
    private final TagRepository tagRepository;
    private final EmploymentAnnouncementTagRepository employmentAnnouncementTagRepository;
    private final MemberRepository memberRepository;
    private final GetUserEmailUtil getUserEmailUtil;
    private final EmploymentAnnouncementRepository employmentAnnouncementRepository;

    @Transactional
    @Override
    public void EmploymentAnnouncementSave(EmploymentAnnouncementSaveDto employmentAnnouncementSaveDto) {
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow();
        employmentAnnouncementRepository.save(employmentAnnouncementSaveDto.toEntityByEmploymentAnnouncement(member));
        for(String i : employmentAnnouncementSaveDto.getTagName()){
            Tag tag = tagRepository.findByTagName(i).orElseThrow(NotFoundTagException::new);
            List<EmploymentAnnouncement> allByEmploymentAnnouncementName = employmentAnnouncementRepository.findAllByEmploymentAnnouncementName(employmentAnnouncementSaveDto.getEmploymentAnnouncementName());
            int size = allByEmploymentAnnouncementName.size() - 1;
            employmentAnnouncementSaveDto.MappingTagByEmploymentAnnouncement(tag, allByEmploymentAnnouncementName.get(size));
            log.info(employmentAnnouncementSaveDto.getEmploymentAnnouncementName());
            employmentAnnouncementTagRepository.save(employmentAnnouncementSaveDto.ToEntityByEmploymentAnnouncementTag());
        }
    }

    @Override
    public EmploymentAnnouncementResponseDto findByOne(Long idx) {
        EmploymentAnnouncementResponseDto employmentAnnouncementResponseDto = employmentAnnouncementRepository.findById(idx)
                .map(m -> mapper.map(m, EmploymentAnnouncementResponseDto.class)).orElseThrow(NotFoundEmploymentAnnouncementException::new);
        EmploymentAnnouncement employmentAnnouncement = employmentAnnouncementRepository.findById(idx).orElseThrow(NotFoundEmploymentAnnouncementException::new);
        List<EmploymentAnnouncementTag> employmentAnnouncementTags = employmentAnnouncementTagRepository.findAllByEmploymentAnnouncement(employmentAnnouncement);
        for (EmploymentAnnouncementTag i : employmentAnnouncementTags) {
            employmentAnnouncementResponseDto.getEmploymentAnnouncementTags().add(i.getTag().getTagName());
        }
        return employmentAnnouncementResponseDto;
    }

    @Override
    public List<EmploymentAnnouncementResponseDto> findByAll() {
        List<EmploymentAnnouncementResponseDto> responseDtoList = new ArrayList<>();
        List<EmploymentAnnouncementResponseDto> employmentAnnouncementResponseDtos = employmentAnnouncementRepository.findAll().stream()
                .map(m -> mapper.map(m, EmploymentAnnouncementResponseDto.class))
                .collect(Collectors.toList());
        LocalDate now = LocalDate.now();
        //?????? ?????? ????????????
        for (EmploymentAnnouncementResponseDto employmentAnnouncementResponseDto : employmentAnnouncementResponseDtos) {
            int compare = employmentAnnouncementResponseDto.getDeadLine().compareTo(now);
            if(compare >= 0) {
                responseDtoList.add(employmentAnnouncementResponseDto);
            }
        }
        //?????? ????????????
        return getEmploymentAnnouncementResponseDtos(responseDtoList);
    }

    @Transactional
    @Override
    public void updateEmploymentAnnouncement(Long idx, EmploymentAnnouncementUpdateDto employmentAnnouncementUpdateDto) {
        EmploymentAnnouncement employmentAnnouncement = employmentAnnouncementRepository.findById(idx).orElseThrow(NotFoundEmploymentAnnouncementException::new);
        UserWriteCheck(employmentAnnouncement);
        //1??? ??????
        employmentAnnouncement.update(employmentAnnouncementUpdateDto);
        //?????? ??????
        List<EmploymentAnnouncementTag> employmentAnnouncementTags = employmentAnnouncementTagRepository.findAllByEmploymentAnnouncement(employmentAnnouncement);
        for (EmploymentAnnouncementTag employmentAnnouncementTag : employmentAnnouncementTags) {
            employmentAnnouncementTagRepository.delete(employmentAnnouncementTag);
        }
        //?????? ??????
        for(String i : employmentAnnouncementUpdateDto.getTagName()){
            Tag tag = tagRepository.findByTagName(i).orElseThrow(NotFoundTagException::new);
            employmentAnnouncementUpdateDto.MappingTagByEmploymentAnnouncement(tag, employmentAnnouncement);
            employmentAnnouncementTagRepository.save(employmentAnnouncementUpdateDto.ToEntityByEmploymentAnnouncementTag());
        }
    }

    @Transactional
    @Override
    public void deleteEmploymentAnnouncement(Long idx) {
        EmploymentAnnouncement employmentAnnouncement = employmentAnnouncementRepository.findById(idx).orElseThrow(NotFoundEmploymentAnnouncementException::new);
        UserWriteCheck(employmentAnnouncement);
        employmentAnnouncementTagRepository.deleteAllByEmploymentAnnouncement(employmentAnnouncement);
        employmentAnnouncementRepository.deleteById(idx);
    }

    @Override
    public List<EmploymentAnnouncementResponseDto> findByEmploymentAnnouncementNameORRecruitmentFieldOREmploymentAnnouncementAddress(String keyword) {
        List<EmploymentAnnouncementResponseDto> employmentAnnouncementResponseDtos = employmentAnnouncementRepository.searchByEmploymentAnnouncementNameORRecruitmentFieldOREmploymentAnnouncementAddressLike(keyword).stream()
                .map(m -> mapper.map(m, EmploymentAnnouncementResponseDto.class))
                .collect(Collectors.toList());
        return getEmploymentAnnouncementResponseDtos(employmentAnnouncementResponseDtos);
    }

    private List<EmploymentAnnouncementResponseDto> getEmploymentAnnouncementResponseDtos(List<EmploymentAnnouncementResponseDto> employmentAnnouncementResponseDtos) {
        for (EmploymentAnnouncementResponseDto i : employmentAnnouncementResponseDtos) {
            EmploymentAnnouncement employmentAnnouncement = employmentAnnouncementRepository.findById(i.getEmploymentAnnouncementIdx()).orElseThrow(NotFoundEmploymentAnnouncementException::new);
            List<EmploymentAnnouncementTag> employmentAnnouncementTags = employmentAnnouncementTagRepository.findAllByEmploymentAnnouncement(employmentAnnouncement);
            for (EmploymentAnnouncementTag j : employmentAnnouncementTags) {
                i.getEmploymentAnnouncementTags().add(j.getTag().getTagName());
            }
        }
        return employmentAnnouncementResponseDtos;
    }

    // ????????? ?????? ?????? Method
    public void UserWriteCheck(EmploymentAnnouncement employmentAnnouncement) {
        Member CurrentUser = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        Member WriteMember = employmentAnnouncementRepository.findById(employmentAnnouncement.getEmploymentAnnouncementIdx()).orElseThrow(CAuthenticationEntryPointException::new).getMember();
        if (!CurrentUser.getMemberEmail().equals(WriteMember.getMemberEmail())) {
            throw new AuthorNotCertifiedException();
        }
    }
}
