package com.gsm.chwijuntime.service.employmentconfirmation;

import com.gsm.chwijuntime.advice.exception.AuthorNotCertifiedException;
import com.gsm.chwijuntime.advice.exception.CAuthenticationEntryPointException;
import com.gsm.chwijuntime.advice.exception.NotFoundEmploymentConfirmationException;
import com.gsm.chwijuntime.advice.exception.NotFoundTagException;
import com.gsm.chwijuntime.dto.employmentconfirmation.EmploymentConfirmationResDto;
import com.gsm.chwijuntime.dto.employmentconfirmation.EmploymentConfirmationSaveDto;
import com.gsm.chwijuntime.dto.employmentconfirmation.EmploymentConfirmationUpdateDto;
import com.gsm.chwijuntime.model.EmploymentConfirmation;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.model.tagmapping.EmploymentConfirmationTag;
import com.gsm.chwijuntime.repository.EmploymentConfirmationRepository;
import com.gsm.chwijuntime.repository.MemberRepository;
import com.gsm.chwijuntime.repository.TagRepository;
import com.gsm.chwijuntime.repository.tag.EmploymentConfirmationTagRepository;
import com.gsm.chwijuntime.util.GetUserEmailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class EmploymentConfirmationServiceImpl implements EmploymentConfirmationService {

    private final GetUserEmailUtil getUserEmailUtil;
    private final ModelMapper mapper;
    private final MemberRepository memberRepository;
    private final TagRepository tagRepository;
    private final EmploymentConfirmationTagRepository employmentConfirmationTagRepository;
    private final EmploymentConfirmationRepository employmentConfirmationRepository;

    @Transactional
    @Override
    public void EmploymentConfirmationServiceSave(EmploymentConfirmationSaveDto employmentConfirmationSaveDto) {
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        log.info(employmentConfirmationSaveDto.getEmploymentConfirmationName());
        employmentConfirmationRepository.save(employmentConfirmationSaveDto.toEntityByEmploymentConfirmation(member));
        for(String i : employmentConfirmationSaveDto.getTagName()) {
            Tag tag = tagRepository.findByTagName(i).orElseThrow(NotFoundTagException::new);
            List<EmploymentConfirmation> allByEmploymentConfirmationName = employmentConfirmationRepository.findAllByEmploymentConfirmationName(employmentConfirmationSaveDto.getEmploymentConfirmationName());
            int size = allByEmploymentConfirmationName.size() - 1;
            employmentConfirmationSaveDto.mappingTagEmploymentConfirmation(tag, allByEmploymentConfirmationName.get(size));
            log.info(employmentConfirmationSaveDto.getEmploymentConfirmationName());
            employmentConfirmationTagRepository.save(employmentConfirmationSaveDto.toEntityByEmploymentConfirmationTag());
        }
    }

    @Override
    public EmploymentConfirmationResDto findByIdx(Long idx) {
        EmploymentConfirmationResDto employmentConfirmationResDto = employmentConfirmationRepository.findById(idx)
                .map(m -> mapper.map(m, EmploymentConfirmationResDto.class)).orElseThrow(NotFoundEmploymentConfirmationException::new);
        EmploymentConfirmation employmentConfirmation = employmentConfirmationRepository.findByEmploymentConfirmationIdx(employmentConfirmationResDto.getEmploymentConfirmationIdx());
        List<EmploymentConfirmationTag> employmentConfirmationTags = employmentConfirmationTagRepository.findAllByEmploymentConfirmation(employmentConfirmation);
        for (EmploymentConfirmationTag i : employmentConfirmationTags) {
            employmentConfirmationResDto.getEmploymentConfirmationTags().add(i.getTag().getTagName());
        }
        return employmentConfirmationResDto;
    }

    @Override
    public List<EmploymentConfirmationResDto> findAll() {
        List<EmploymentConfirmationResDto> employmentConfirmationResDtos = employmentConfirmationRepository.findAll().stream()
                .map(m -> mapper.map(m, EmploymentConfirmationResDto.class))
                .collect(Collectors.toList());
        //태그 보여주기
        return getEmploymentConfirmationResDtos(employmentConfirmationResDtos);
    }

    @Transactional
    @Override
    public void updateEmploymentConfirmation(Long idx, EmploymentConfirmationUpdateDto employmentConfirmationUpdateDto) {
        UserWriteCheck(idx);
        EmploymentConfirmation employmentConfirmation = employmentConfirmationRepository.findById(idx).orElseThrow(NotFoundEmploymentConfirmationException::new);
        //1차 수정
        employmentConfirmation.changeEmploymentConfirmation(employmentConfirmationUpdateDto);
        //태그 삭제
        List<EmploymentConfirmationTag> employmentConfirmationTags = employmentConfirmationTagRepository.findAllByEmploymentConfirmation(employmentConfirmation);
        for (EmploymentConfirmationTag employmentConfirmationTag : employmentConfirmationTags) {
            employmentConfirmationTagRepository.delete(employmentConfirmationTag);
        }
        // 태그 저장
        for(String i : employmentConfirmationUpdateDto.getTagName()) {
            Tag tag = tagRepository.findByTagName(i).orElseThrow(NotFoundTagException::new);
            employmentConfirmationUpdateDto.mappingTagEmploymentConfirmation(tag, employmentConfirmation);
            employmentConfirmationTagRepository.save(employmentConfirmationUpdateDto.toEntityByEmploymentConfirmationTag());
        }
    }

    @Transactional
    @Override
    public void deleteEmploymentConfirmation(Long idx) {
        UserWriteCheck(idx);
        EmploymentConfirmation byEmploymentConfirmationIdx = employmentConfirmationRepository.findByEmploymentConfirmationIdx(idx);
        employmentConfirmationTagRepository.deleteAllByEmploymentConfirmation(byEmploymentConfirmationIdx);
        employmentConfirmationRepository.deleteById(idx);
    }

    @Override
    public List<EmploymentConfirmationResDto> findByEmploymentConfirmationNameOREmploymentConfirmationAreasOREmploymentConfirmationJockey(String keyword) {
        List<EmploymentConfirmationResDto> collect = employmentConfirmationRepository.searchByEmploymentConfirmationNameOREmploymentConfirmationAreasOREmploymentConfirmationJockey(keyword).stream()
                .map(m -> mapper.map(m, EmploymentConfirmationResDto.class))
                .collect(Collectors.toList());
        return getEmploymentConfirmationResDtos(collect);
    }

    private List<EmploymentConfirmationResDto> getEmploymentConfirmationResDtos(List<EmploymentConfirmationResDto> collect) {
        for (EmploymentConfirmationResDto i : collect) {
            EmploymentConfirmation employmentConfirmation = employmentConfirmationRepository.findById(i.getEmploymentConfirmationIdx()).orElseThrow(NotFoundEmploymentConfirmationException::new);
            List<EmploymentConfirmationTag> employmentConfirmationTags = employmentConfirmationTagRepository.findAllByEmploymentConfirmation(employmentConfirmation);
            for (EmploymentConfirmationTag j : employmentConfirmationTags) {
                i.getEmploymentConfirmationTags().add(j.getTag().getTagName());
            }
        }
        return collect;
    }

    //작성자 권한 체크 Method
    public void UserWriteCheck(Long idx) {
        Member CurrentUser = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        Member WriteMember = employmentConfirmationRepository.findById(idx).orElseThrow(CAuthenticationEntryPointException::new).getMember();
        if (!CurrentUser.getMemberEmail().equals(WriteMember.getMemberEmail())) {
            throw new AuthorNotCertifiedException();
        }
    }
}
