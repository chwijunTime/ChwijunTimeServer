package com.gsm.chwijuntime.service.companyreview;

import com.gsm.chwijuntime.dto.companyreview.CompanyReviewResDto;
import com.gsm.chwijuntime.dto.companyreview.CompanyReviewSaveDto;
import com.gsm.chwijuntime.dto.companyreview.CompanyUpdateDto;
import com.gsm.chwijuntime.model.CompanyReview;
import com.gsm.chwijuntime.model.Member;

import java.util.List;

public interface CompanyReviewService {
    void insertCompanyReview(CompanyReviewSaveDto companyReviewSaveDto);
    List<CompanyReviewResDto> findAll();
    CompanyReviewResDto findByIdx(Long idx);
    void deleteByIdx(Long idx);
    void update(Long idx, CompanyUpdateDto companyUpdateDto);
    List<CompanyReviewResDto> findByMember();
}
