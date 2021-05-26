package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.EmploymentConfirmation;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmploymentConfirmationRepository extends JpaRepository<EmploymentConfirmation, Long> {
    @Query("select e from EmploymentConfirmation e join fetch e.member order by e.employmentConfirmationIdx desc")
    List<EmploymentConfirmation> findAll();

    @Query("select e from EmploymentConfirmation e join fetch e.member where e.employmentConfirmationIdx = :idx")
    EmploymentConfirmation findByEmploymentConfirmationIdx(Long idx);

    List<EmploymentConfirmation> findAllByEmploymentConfirmationName(String name);

    @Query("select e from EmploymentConfirmation e join fetch e.member where e.employmentConfirmationName like %:keyword% or e.employmentConfirmationAreas like %:keyword% or e.employmentConfirmationJockey like %:keyword% order by e.employmentConfirmationIdx desc")
    List<EmploymentConfirmation> searchByEmploymentConfirmationNameOREmploymentConfirmationAreasOREmploymentConfirmationJockey(@Param("keyword") String keyword);
}
