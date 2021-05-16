package com.gsm.chwijuntime.service.employmentAnnouncement;

import com.gsm.chwijuntime.dto.contractingcompany.ContractingCompanyResDto;
import com.gsm.chwijuntime.dto.employmentAnnouncement.EmploymentAnnouncementResponseDto;
import com.gsm.chwijuntime.dto.employmentAnnouncement.EmploymentAnnouncementSaveDto;
import com.gsm.chwijuntime.dto.employmentAnnouncement.EmploymentAnnouncementUpdateDto;
import com.gsm.chwijuntime.model.CompanyReview;
import com.gsm.chwijuntime.model.EmploymentAnnouncement;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmploymentAnnouncementService {
    void EmploymentAnnouncementSave(EmploymentAnnouncementSaveDto employmentAnnouncementSaveDto);
    EmploymentAnnouncementResponseDto findByOne(Long idx);
    List<EmploymentAnnouncementResponseDto> findByAll();
    void updateEmploymentAnnouncement(Long idx, EmploymentAnnouncementUpdateDto employmentAnnouncementUpdateDto);
    void deleteEmploymentAnnouncement(Long idx);

    //회사 이름 OR 채용 분야 OR 회사 위치 검색
    List<EmploymentAnnouncementResponseDto> findByEmploymentAnnouncementNameORRecruitmentFieldOREmploymentAnnouncementAddress(String keyword);

}