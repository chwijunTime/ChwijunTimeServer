package com.gsm.chwijuntime.service.employmentconfirmation;

import com.gsm.chwijuntime.advice.exception.CAuthenticationEntryPointException;
import com.gsm.chwijuntime.dto.employmentconfirmation.EmploymentConfirmationSaveDto;
import com.gsm.chwijuntime.dto.employmentconfirmation.EmploymentConfirmationUpdateDto;
import com.gsm.chwijuntime.model.EmploymentConfirmation;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.repository.EmploymentConfirmationRepository;
import com.gsm.chwijuntime.repository.MemberRepository;
import com.gsm.chwijuntime.util.GetUserEmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmploymentConfirmationServiceImpl implements EmploymentConfirmationService {

    private final GetUserEmailUtil getUserEmailUtil;
    private final MemberRepository memberRepository;
    private final EmploymentConfirmationRepository employmentConfirmationRepository;

    @Override
    public void EmploymentConfirmationServiceSave(EmploymentConfirmationSaveDto employmentConfirmationSaveDto) {
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.GetUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        employmentConfirmationRepository.save(employmentConfirmationSaveDto.toEntityByEmploymentConfirmation(member));
    }

    @Override
    public EmploymentConfirmation findByIdx(Long idx) {
        return employmentConfirmationRepository.findByEmploymentConfirmationIdx(idx);
    }

    @Override
    public List<EmploymentConfirmation> findAll() {
        return employmentConfirmationRepository.findAll();
    }

    @Transactional
    @Override
    public void updateEmploymentConfirmation(Long idx, EmploymentConfirmationUpdateDto employmentConfirmationUpdateDto) {
        EmploymentConfirmation employmentConfirmation = employmentConfirmationRepository.findById(idx).orElseThrow(null);
        employmentConfirmation.changeEmploymentConfirmation(employmentConfirmationUpdateDto);
    }

    @Override
    public void deleteEmploymentConfirmation(Long idx) {
        employmentConfirmationRepository.deleteById(idx);
    }
}
