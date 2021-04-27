package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.EmploymentConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentConfirmationRepository extends JpaRepository<EmploymentConfirmation, Long> {

}
