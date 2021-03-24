package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.MemberResume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberResumeRepository extends JpaRepository<MemberResume, Long> {

}
