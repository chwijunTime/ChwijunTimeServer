package com.gsm.chwijuntime.service.employmentAnnouncement;

import com.gsm.chwijuntime.dto.employmentAnnouncement.EmploymentAnnouncementResponseDto;
import com.gsm.chwijuntime.dto.employmentAnnouncement.EmploymentAnnouncementSaveDto;
import com.gsm.chwijuntime.dto.employmentAnnouncement.EmploymentAnnouncementUpdateDto;
import com.gsm.chwijuntime.model.EmploymentAnnouncement;

import java.util.List;

public interface EmploymentAnnouncementService {

    void EmploymentAnnouncementSave(EmploymentAnnouncementSaveDto employmentAnnouncementSaveDto);
    EmploymentAnnouncementResponseDto findByOne(Long idx);
    List<EmploymentAnnouncementResponseDto> findByAll();
    void updateEmploymentAnnouncement(Long idx, EmploymentAnnouncementUpdateDto employmentAnnouncementUpdateDto);
    void deleteEmploymentAnnouncement(Long idx);

}