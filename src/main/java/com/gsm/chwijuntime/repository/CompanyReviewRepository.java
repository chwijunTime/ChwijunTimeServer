package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.dto.companyreview.CompanyReviewResDto;
import com.gsm.chwijuntime.model.CompanyReview;
import com.gsm.chwijuntime.model.Member;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyReviewRepository extends JpaRepository<CompanyReview, Long> {

    List<CompanyReview> findAllByCompanyName(String name);
    List<CompanyReview> findByMember(Member member);

    @Query("select c from CompanyReview c join fetch c.member order by c.companyReviewIdx desc")
    List<CompanyReview> findAll();

    List<CompanyReview> findByCompanyName(String name);

    @Query("SELECT c FROM CompanyReview c WHERE c.companyName LIKE %:companyNameKeyword% order by c.companyReviewIdx desc")
    List<CompanyReview> searchByCompanyNameKeywordLike(@Param("companyNameKeyword") String companyNameKeyword);
}
