package com.gsm.chwijuntime.service.employmentconfirmation;

import com.gsm.chwijuntime.dto.employmentconfirmation.EmploymentConfirmationSaveDto;
import com.gsm.chwijuntime.dto.employmentconfirmation.EmploymentConfirmationUpdateDto;
import com.gsm.chwijuntime.model.EmploymentConfirmation;

import java.util.List;

public interface EmploymentConfirmationService {


    void EmploymentConfirmationServiceSave(EmploymentConfirmationSaveDto employmentConfirmationSaveDto);
    EmploymentConfirmation findByIdx(Long idx);
    List<EmploymentConfirmation> findAll();
    void updateEmploymentConfirmation(Long idx, EmploymentConfirmationUpdateDto employmentConfirmationUpdateDto);
    void deleteEmploymentConfirmation(Long idx);

}
