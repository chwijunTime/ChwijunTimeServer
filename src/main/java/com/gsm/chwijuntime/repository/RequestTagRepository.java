package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.RequestTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestTagRepository extends JpaRepository<RequestTag, Long> {

    @Query("select r from RequestTag r order by r.tagIdx desc")
    List<RequestTag> searchAll();

    RequestTag findByTagName(String name);
}
