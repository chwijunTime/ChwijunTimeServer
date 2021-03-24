package com.gsm.chwijuntime.repository.tag;

import com.gsm.chwijuntime.model.tagmapping.EmploymentConfirmationTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentConfirmationTagRepository extends JpaRepository<EmploymentConfirmationTag, Long> {

}
