package com.gsm.chwijuntime.service.companyreview;

import com.gsm.chwijuntime.advice.exception.AuthorNotCertifiedException;
import com.gsm.chwijuntime.advice.exception.CAuthenticationEntryPointException;
import com.gsm.chwijuntime.advice.exception.NotFoundCompanyReviewException;
import com.gsm.chwijuntime.advice.exception.NotFoundTagException;
import com.gsm.chwijuntime.dto.companyreview.CompanyReviewResDto;
import com.gsm.chwijuntime.dto.companyreview.CompanyReviewSaveDto;
import com.gsm.chwijuntime.dto.companyreview.CompanyUpdateDto;
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

    @Transactional
    @Override
    public void insertCompanyReview(CompanyReviewSaveDto companyReviewSaveDto) {
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        companyReviewRepository.save(companyReviewSaveDto.ToEntityByContractingCompany(member));
        for (String i: companyReviewSaveDto.getTagName()) {
            Tag tag = tagRepository.findByTagName(i);
            if(tag == null) {
                throw new NotFoundTagException();
            }
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
        return getCompanyReviewResDtos(companyReviewResDtos);
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

    @Transactional
    @Override
    public void update(Long idx, CompanyUpdateDto companyUpdateDto) {
        UserWriteCheck(idx);
        CompanyReview companyReview = companyReviewRepository.findById(idx).orElseThrow(NotFoundCompanyReviewException::new);
        // 1번째 수정
        companyReview.changeCompanyReview(companyUpdateDto);
        List<CompanyReviewTag> companyReviewTags = companyReviewTagRepository.findAllByCompanyReview(companyReview);
        // 관련된 태그 전부 삭제
        for (CompanyReviewTag companyReviewTag : companyReviewTags) {
            companyReviewTagRepository.delete(companyReviewTag);
        }
        // 태그 저장
        for (String i: companyUpdateDto.getTagName()) {
            Tag tag = tagRepository.findByTagName(i);
            if(tag == null) {
                throw new NotFoundTagException();
            }
            companyUpdateDto.MappingTag_ContractingCompany(tag, companyReview);
            companyReviewTagRepository.save(companyUpdateDto.ToEntityByCompanyReviewTag());
        }
    }

    @Override
    public List<CompanyReviewResDto> findByMember() {
        Member findMember = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        List<CompanyReviewResDto> companyReviewResDtos = companyReviewRepository.findByMember(findMember).stream()
                .map(m -> mapper.map(m, CompanyReviewResDto.class))
                .collect(Collectors.toList());
        return getCompanyReviewResDtos(companyReviewResDtos);
    }

    @Override
    public List<CompanyReviewResDto> findByCompanyNameKeyword(String companyNameKeyword) {
        List<CompanyReviewResDto> collect = companyReviewRepository.searchByCompanyNameKeywordLike(companyNameKeyword).stream()
                .map(m -> mapper.map(m, CompanyReviewResDto.class))
                .collect(Collectors.toList());
        return getCompanyReviewResDtos(collect);
    }

    @Transactional
    @Override
    public void deleteByIdx(Long idx) {
        UserWriteCheck(idx);
        CompanyReview companyReview = companyReviewRepository.findById(idx).orElseThrow(null);
        companyReviewTagRepository.deleteAllByCompanyReview(companyReview);
        companyReviewRepository.deleteById(idx);
    }

    private List<CompanyReviewResDto> getCompanyReviewResDtos(List<CompanyReviewResDto> companyReviewResDtos) {
        for (CompanyReviewResDto i : companyReviewResDtos) {
            CompanyReview companyReview = companyReviewRepository.findById(i.getCompanyReviewIdx()).orElseThrow(NotFoundCompanyReviewException::new);
            List<CompanyReviewTag> companyReviewTags = companyReviewTagRepository.findAllByCompanyReview(companyReview);
            for (CompanyReviewTag j : companyReviewTags) {
                i.getCompanyReviewTags().add(j.getTag().getTagName());
            }
        }
        return companyReviewResDtos;
    }

    //작성자 권한 체크 Method
    private void UserWriteCheck(Long idx) {
        Member CurrentUser = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        Member WriteMember = companyReviewRepository.findById(idx).orElseThrow(CAuthenticationEntryPointException::new).getMember();
        if (!CurrentUser.getMemberEmail().equals(WriteMember.getMemberEmail())) {
            throw new AuthorNotCertifiedException();
        }
    }
}
