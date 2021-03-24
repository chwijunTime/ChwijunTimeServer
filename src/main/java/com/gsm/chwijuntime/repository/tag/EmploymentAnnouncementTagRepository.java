package com.gsm.chwijuntime.repository.tag;

import com.gsm.chwijuntime.model.tagmapping.EmploymentAnnouncementTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentAnnouncementTagRepository extends JpaRepository<EmploymentAnnouncementTag, Long> {

}
