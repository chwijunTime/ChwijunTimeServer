package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.ApplicationEmployment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationEmploymentRepository extends JpaRepository<ApplicationEmployment, Long> {

    @Query("select a from ApplicationEmployment a join fetch a.member join fetch a.employmentAnnouncement")
    List<ApplicationEmployment> findAll();

}