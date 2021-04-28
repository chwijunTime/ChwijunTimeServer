package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.EmploymentConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmploymentConfirmationRepository extends JpaRepository<EmploymentConfirmation, Long> {

    @Query("select e from EmploymentConfirmation e join fetch e.member")
    List<EmploymentConfirmation> findAll();

    @Query("select e from EmploymentConfirmation e join fetch e.member where e.employmentConfirmationIdx = :idx")
    EmploymentConfirmation findByEmploymentConfirmationIdx(Long idx);

    List<EmploymentConfirmation> findAllByEmploymentConfirmationName(String name);
}
