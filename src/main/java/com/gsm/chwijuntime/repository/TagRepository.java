package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByTagName(String tagName);

    @Query("select t from Tag t order by t.tagIdx desc")
    List<Tag> findAll();
}
