package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Query("select n from Notice n join fetch n.member order by n.noticeIdx desc")
    List<Notice> findAll();

}
