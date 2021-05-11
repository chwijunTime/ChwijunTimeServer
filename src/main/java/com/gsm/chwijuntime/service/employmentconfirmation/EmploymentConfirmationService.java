package com.gsm.chwijuntime.service.employmentconfirmation;

import com.gsm.chwijuntime.dto.employmentconfirmation.EmploymentConfirmationResDto;
import com.gsm.chwijuntime.dto.employmentconfirmation.EmploymentConfirmationSaveDto;
import com.gsm.chwijuntime.dto.employmentconfirmation.EmploymentConfirmationUpdateDto;
import com.gsm.chwijuntime.model.EmploymentConfirmation;

import java.util.List;

public interface EmploymentConfirmationService {
    void EmploymentConfirmationServiceSave(EmploymentConfirmationSaveDto employmentConfirmationSaveDto);
    EmploymentConfirmationResDto findByIdx(Long idx);
    List<EmploymentConfirmationResDto> findAll();
    void updateEmploymentConfirmation(Long idx, EmploymentConfirmationUpdateDto employmentConfirmationUpdateDto);
}
