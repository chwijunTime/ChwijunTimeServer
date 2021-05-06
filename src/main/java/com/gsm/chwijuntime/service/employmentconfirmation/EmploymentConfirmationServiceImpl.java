package com.gsm.chwijuntime.service.employmentconfirmation;

import com.gsm.chwijuntime.advice.exception.CAuthenticationEntryPointException;
import com.gsm.chwijuntime.advice.exception.NotFoundEmploymentConfirmationException;
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
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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
        employmentConfirmationRepository.save(employmentConfirmationSaveDto.toEntityByEmploymentConfirmation(member));
        for(String i : employmentConfirmationSaveDto.getTagName()) {
            Tag tag = tagRepository.findByTagName(i);
            List<EmploymentConfirmation> allByEmploymentConfirmationName = employmentConfirmationRepository.findAllByEmploymentConfirmationName(employmentConfirmationSaveDto.getEmploymentConfirmationName());
            int size = allByEmploymentConfirmationName.size() - 1;
            employmentConfirmationSaveDto.mappingTagEmploymentConfirmation(tag, allByEmploymentConfirmationName.get(size));
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
        for (EmploymentConfirmationResDto i : employmentConfirmationResDtos) {
            EmploymentConfirmation employmentConfirmation = employmentConfirmationRepository.findById(i.getEmploymentConfirmationIdx()).orElseThrow(NotFoundEmploymentConfirmationException::new);
            List<EmploymentConfirmationTag> employmentConfirmationTags = employmentConfirmationTagRepository.findAllByEmploymentConfirmation(employmentConfirmation);
            for (EmploymentConfirmationTag j : employmentConfirmationTags) {
                i.getEmploymentConfirmationTags().add(j.getTag().getTagName());
            }
        }
        return employmentConfirmationResDtos;
    }

    @Transactional
    @Override
    public void updateEmploymentConfirmation(Long idx, EmploymentConfirmationUpdateDto employmentConfirmationUpdateDto) {
        EmploymentConfirmation employmentConfirmation = employmentConfirmationRepository.findById(idx).orElseThrow(NotFoundEmploymentConfirmationException::new);
        employmentConfirmation.changeEmploymentConfirmation(employmentConfirmationUpdateDto);
    }

    @Transactional
    @Override
    public void deleteEmploymentConfirmation(Long idx) {
        //삭제는 없음 (에러 걸림)
    }

}
