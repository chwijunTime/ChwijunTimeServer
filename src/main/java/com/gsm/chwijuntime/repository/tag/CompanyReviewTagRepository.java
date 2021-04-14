package com.gsm.chwijuntime.repository.tag;

import com.gsm.chwijuntime.model.CompanyReview;
import com.gsm.chwijuntime.model.tagmapping.CompanyReviewTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyReviewTagRepository extends JpaRepository<CompanyReviewTag, Long> {

    List<CompanyReviewTag> findAllByCompanyReview(CompanyReview companyReview);

}
