package com.gsm.chwijuntime.service.employmentconfirmation;

import com.gsm.chwijuntime.dto.employmentconfirmation.EmploymentConfirmationResDto;
import com.gsm.chwijuntime.dto.employmentconfirmation.EmploymentConfirmationSaveDto;
import com.gsm.chwijuntime.dto.employmentconfirmation.EmploymentConfirmationUpdateDto;

import java.util.List;

public interface EmploymentConfirmationService {
    void EmploymentConfirmationServiceSave(EmploymentConfirmationSaveDto employmentConfirmationSaveDto);
    EmploymentConfirmationResDto findByIdx(Long idx);
    List<EmploymentConfirmationResDto> findAll();
    void updateEmploymentConfirmation(Long idx, EmploymentConfirmationUpdateDto employmentConfirmationUpdateDto);
    void deleteEmploymentConfirmation(Long idx);

    // 회사 이름 OR 지역 검색 OR 기수 검색
    List<EmploymentConfirmationResDto> findByEmploymentConfirmationNameOREmploymentConfirmationAreasOREmploymentConfirmationJockey(String keyword);
}
