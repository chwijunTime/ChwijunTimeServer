package com.gsm.chwijuntime.repository.tag;

import com.gsm.chwijuntime.model.TipsStorage;
import com.gsm.chwijuntime.model.tagmapping.TipsStorageTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipsStorageTagRepository extends JpaRepository<TipsStorageTag, Long> {

    List<TipsStorageTag> findAllByTipsStorage(TipsStorage tipsStorage);
    void deleteAllByTipsStorage(TipsStorage tipsStorage);

}
