package com.gsm.chwijuntime.repository.tag;

import com.gsm.chwijuntime.model.tagmapping.MemberPortfolioTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberPortfolioTagRepository extends JpaRepository<MemberPortfolioTag, Long> {

}
