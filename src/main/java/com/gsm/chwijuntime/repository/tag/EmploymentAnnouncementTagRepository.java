package com.gsm.chwijuntime.repository.tag;

import com.gsm.chwijuntime.model.EmploymentAnnouncement;
import com.gsm.chwijuntime.model.tagmapping.EmploymentAnnouncementTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmploymentAnnouncementTagRepository extends JpaRepository<EmploymentAnnouncementTag, Long> {

    List<EmploymentAnnouncementTag> findAllByEmploymentAnnouncement(EmploymentAnnouncement employmentAnnouncement);

}
