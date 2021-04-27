package com.gsm.chwijuntime.repository.tag;

import com.gsm.chwijuntime.model.tagmapping.TipsStorageTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipsStorageTagRepository extends JpaRepository<TipsStorageTag, Long> {

}
