package com.gsm.chwijuntime.service.companyreview;

import com.gsm.chwijuntime.advice.exception.CAuthenticationEntryPointException;
import com.gsm.chwijuntime.dto.companyreview.CompanyReviewSaveDto;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.repository.CompanyReviewRepository;
import com.gsm.chwijuntime.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CompanyReviewServiceImpl implements CompanyReviewService {

    private final MemberRepository memberRepository;
    private final CompanyReviewRepository companyReviewRepository;

    @Override
    public void insertCompanyReview(CompanyReviewSaveDto companyReviewSaveDto) {
        Member member = memberRepository.findByMemberEmail(GetUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        companyReviewRepository.save(companyReviewSaveDto.ToEntityByContractingCompany(member));
    }





    //현재 사용자의 ID를 Return
    public String GetUserEmail() {
        String userEmail;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            userEmail = ((UserDetails)principal).getUsername();
        } else {
            userEmail = principal.toString();
        }
        return userEmail;
    }
}
