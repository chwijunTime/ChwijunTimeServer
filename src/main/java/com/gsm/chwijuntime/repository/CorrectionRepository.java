package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.Correction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorrectionRepository extends JpaRepository<Correction, Long> {

    @Query("select c from Correction c join fetch c.correctionApply where c.classNumber = :classNumber order by c.correctionIdx desc")
    List<Correction> findByClassNumber(String classNumber);

}
