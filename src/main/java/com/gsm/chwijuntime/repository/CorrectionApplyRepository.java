package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.CorrectionApply;
import com.gsm.chwijuntime.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorrectionApplyRepository extends JpaRepository<CorrectionApply, Long> {

    List<CorrectionApply> findByMember(Member member);

    @Query("select c from CorrectionApply c order by c.correctionApplyIdx desc")
    List<CorrectionApply> findAll();
}
