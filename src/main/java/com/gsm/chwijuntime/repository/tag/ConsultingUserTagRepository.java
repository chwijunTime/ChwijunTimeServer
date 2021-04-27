package com.gsm.chwijuntime.repository.tag;

import com.gsm.chwijuntime.model.tagmapping.ConsultingUserTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultingUserTagRepository extends JpaRepository<ConsultingUserTag, Long> {

}
