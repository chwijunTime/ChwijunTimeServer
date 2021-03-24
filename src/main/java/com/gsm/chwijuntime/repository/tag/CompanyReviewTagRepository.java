package com.gsm.chwijuntime.repository.tag;

import com.gsm.chwijuntime.model.tagmapping.CompanyReviewTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyReviewTagRepository extends JpaRepository<CompanyReviewTag, Long> {

}
