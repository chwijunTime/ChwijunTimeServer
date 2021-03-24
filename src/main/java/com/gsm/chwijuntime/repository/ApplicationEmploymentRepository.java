package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.ApplicationEmployment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationEmploymentRepository extends JpaRepository<ApplicationEmployment, Long> {

}
