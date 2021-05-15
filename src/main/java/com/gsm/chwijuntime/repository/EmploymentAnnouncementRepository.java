package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.EmploymentAnnouncement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmploymentAnnouncementRepository extends JpaRepository<EmploymentAnnouncement, Long> {


    List<EmploymentAnnouncement> findAllByEmploymentAnnouncementName(String name);

    @Query("select e from EmploymentAnnouncement e join fetch e.member order by e.employmentAnnouncementIdx desc")
    List<EmploymentAnnouncement> findAll();

}
