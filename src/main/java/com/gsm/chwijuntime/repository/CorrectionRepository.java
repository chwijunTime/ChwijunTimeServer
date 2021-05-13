package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.Correction;
import com.gsm.chwijuntime.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorrectionRepository extends JpaRepository<Correction, Long> {

    List<Correction> findByMember(Member member);

}
