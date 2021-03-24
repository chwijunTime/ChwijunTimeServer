package com.gsm.chwijuntime.repository.tag;

import com.gsm.chwijuntime.model.tagmapping.MemberTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberTagRepository extends JpaRepository<MemberTag, Long> {

}
