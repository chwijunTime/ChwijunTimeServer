package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    Tag findByTagName(String tagName);
    List<Tag> findAllByTagIdx(Long idx);

}
