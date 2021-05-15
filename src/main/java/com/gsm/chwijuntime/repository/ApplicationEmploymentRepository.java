package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.ApplicationEmployment;
import com.gsm.chwijuntime.model.ApplicationEmploymentStatus;
import com.gsm.chwijuntime.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationEmploymentRepository extends JpaRepository<ApplicationEmployment, Long> {

    @Query("select a from ApplicationEmployment a join fetch a.member join fetch a.employmentAnnouncement order by a.applicationEmploymentIdx desc")
    List<ApplicationEmployment> findAll();

    @Query("select a from ApplicationEmployment a join fetch a.member join fetch a.employmentAnnouncement where a.applicationEmploymentIdx = :idx")
    ApplicationEmployment findByApplicationEmploymentIdx(Long idx);

    List<ApplicationEmployment> findByApplicationEmploymentStatus(ApplicationEmploymentStatus applicationEmploymentStatus);

    List<ApplicationEmployment> findByMember(Member member);
}