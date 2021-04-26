package com.gsm.chwijuntime.service.companyreview;

import com.gsm.chwijuntime.advice.exception.AuthorNotCertifiedException;
import com.gsm.chwijuntime.advice.exception.CAuthenticationEntryPointException;
import com.gsm.chwijuntime.advice.exception.NotFoundCompanyReviewException;
import com.gsm.chwijuntime.dto.companyreview.CompanyReviewResDto;
import com.gsm.chwijuntime.dto.companyreview.CompanyReviewSaveDto;
import com.gsm.chwijuntime.model.CompanyReview;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.model.tagmapping.CompanyReviewTag;
import com.gsm.chwijuntime.repository.CompanyReviewRepository;
import com.gsm.chwijuntime.repository.MemberRepository;
import com.gsm.chwijuntime.repository.TagRepository;
import com.gsm.chwijuntime.repository.tag.CompanyReviewTagRepository;
import com.gsm.chwijuntime.util.GetUserEmailUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CompanyReviewServiceImpl implements CompanyReviewService {

    private final MemberRepository memberRepository;
    private final CompanyReviewRepository companyReviewRepository;
    private final CompanyReviewTagRepository companyReviewTagRepository;
    private final TagRepository tagRepository;
    private final ModelMapper mapper;
    private final GetUserEmailUtil getUserEmailUtil;

    @Override
    public void insertCompanyReview(CompanyReviewSaveDto companyReviewSaveDto) {
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.GetUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        companyReviewRepository.save(companyReviewSaveDto.ToEntityByContractingCompany(member));
        for (String i: companyReviewSaveDto.getTagName()) {
            Tag tag = tagRepository.findByTagName(i);
            List<CompanyReview> allByCompanyName = companyReviewRepository.findAllByCompanyName(companyReviewSaveDto.getCompanyName());
            int size = allByCompanyName.size() - 1;
            companyReviewSaveDto.MappingTag_ContractingCompany(tag, allByCompanyName.get(size));
            companyReviewTagRepository.save(companyReviewSaveDto.ToEntityByCompanyReviewTag());
        }
    }

    @Override
    public List<CompanyReviewResDto> findAll() {
        List<CompanyReviewResDto> companyReviewResDtos = companyReviewRepository.findAll().stream()
                .map(m -> mapper.map(m, CompanyReviewResDto.class))
                .collect(Collectors.toList());
        for (CompanyReviewResDto i : companyReviewResDtos) {
            CompanyReview companyReview = companyReviewRepository.findById(i.getCompanyReviewIdx()).orElseThrow(NotFoundCompanyReviewException::new);
            List<CompanyReviewTag> companyReviewTags = companyReviewTagRepository.findAllByCompanyReview(companyReview);
            for (CompanyReviewTag j : companyReviewTags) {
                i.getCompanyReviewTags().add(j.getTag().getTagName());
            }
        }
        return companyReviewResDtos;
    }

    @Override
    public CompanyReviewResDto findByIdx(Long idx) {
        CompanyReviewResDto companyReviewResDto = companyReviewRepository.findById(idx)
                .map(m -> mapper.map(m, CompanyReviewResDto.class)).orElseThrow(NotFoundCompanyReviewException::new);
        CompanyReview companyReview = companyReviewRepository.findById(idx).orElseThrow(NotFoundCompanyReviewException::new);
        List<CompanyReviewTag> companyReviewTags = companyReviewTagRepository.findAllByCompanyReview(companyReview);
        for (CompanyReviewTag i : companyReviewTags) {
            companyReviewResDto.getCompanyReviewTags().add(i.getTag().getTagName());
        }
        return companyReviewResDto;
    }

    @Override
    public void deleteByIdx(Long idx) {
        UserWriteCheck(idx);
        companyReviewRepository.deleteById(idx);
    }

    //작성자 권한 체크
    public void UserWriteCheck(Long idx) {
        Member CurrentUser = memberRepository.findByMemberEmail(getUserEmailUtil.GetUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        Member WriteMember = companyReviewRepository.findById(idx).orElseThrow(CAuthenticationEntryPointException::new).getMember();
        if (!CurrentUser.getMemberEmail().equals(WriteMember.getMemberEmail())) {
            throw new AuthorNotCertifiedException();
        }
    }

}