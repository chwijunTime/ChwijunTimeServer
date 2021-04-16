package com.gsm.chwijuntime.service.employmentAnnouncement;

import com.gsm.chwijuntime.dto.employmentAnnouncement.EmploymentAnnouncementSaveDto;
import com.gsm.chwijuntime.dto.employmentAnnouncement.EmploymentAnnouncementUpdateDto;
import com.gsm.chwijuntime.model.EmploymentAnnouncement;

import java.util.List;

public interface EmploymentAnnouncementService {

    void EmploymentAnnouncementSave(EmploymentAnnouncementSaveDto employmentAnnouncementSaveDto);
    EmploymentAnnouncement findByOne(Long idx);
    List<EmploymentAnnouncement> findByAll();
    void updateEmploymentAnnouncement(Long idx, EmploymentAnnouncementUpdateDto employmentAnnouncementUpdateDto);
    void deleteEmploymentAnnouncement(Long idx);

}