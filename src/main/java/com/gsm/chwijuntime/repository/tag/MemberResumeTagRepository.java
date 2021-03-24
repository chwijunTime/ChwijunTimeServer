package com.gsm.chwijuntime.repository.tag;

import com.gsm.chwijuntime.model.tagmapping.MemberResumeTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberResumeTagRepository extends JpaRepository<MemberResumeTag, Long> {

}
