package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.dto.companyreview.CompanyReviewResDto;
import com.gsm.chwijuntime.model.CompanyReview;
import com.gsm.chwijuntime.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyReviewRepository extends JpaRepository<CompanyReview, Long> {

    List<CompanyReview> findAllByCompanyName(String name);
    List<CompanyReview> findByMember(Member member);
}
