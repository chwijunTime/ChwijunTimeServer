package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.Correction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorrectionRepository extends JpaRepository<Correction, Long> {



}
