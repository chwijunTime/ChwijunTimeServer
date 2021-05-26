package com.gsm.chwijuntime.repository.tag;

import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.tagmapping.MemberTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberTagRepository extends JpaRepository<MemberTag, Long> {

    List<MemberTag> findByMember(Member member);
}
