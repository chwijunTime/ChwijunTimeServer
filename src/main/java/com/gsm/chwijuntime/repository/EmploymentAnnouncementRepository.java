package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.EmploymentAnnouncement;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmploymentAnnouncementRepository extends JpaRepository<EmploymentAnnouncement, Long> {
    List<EmploymentAnnouncement> findAllByEmploymentAnnouncementName(String name);

    @Query("select e from EmploymentAnnouncement e join fetch e.member order by e.employmentAnnouncementIdx desc")
    List<EmploymentAnnouncement> findAll();

    @Query("select e from EmploymentAnnouncement e  join fetch e.member where e.employmentAnnouncementName like %:keyword% or e.employmentAnnouncementAddress like %:keyword% or e.recruitmentField like %:keyword% order by e.employmentAnnouncementIdx desc")
    List<EmploymentAnnouncement> searchByEmploymentAnnouncementNameORRecruitmentFieldOREmploymentAnnouncementAddressLike(@Param("keyword") String keyword);
}
