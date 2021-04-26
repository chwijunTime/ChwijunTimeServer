package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.EmploymentAnnouncement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmploymentAnnouncementRepository extends JpaRepository<EmploymentAnnouncement, Long> {


    List<EmploymentAnnouncement> findAllByEmploymentAnnouncementName(String name);

}
