package com.gsm.chwijuntime.service.contractingcompany;

import com.gsm.chwijuntime.advice.exception.*;
import com.gsm.chwijuntime.dto.contractingcompany.ContractingCompanyResDto;
import com.gsm.chwijuntime.dto.contractingcompany.ContractingCompanySaveDto;
import com.gsm.chwijuntime.dto.contractingcompany.ContractionCompanyUpdateDto;
import com.gsm.chwijuntime.model.ContractingCompany;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.model.tagmapping.ContractingCompanyTag;
import com.gsm.chwijuntime.repository.ContractingCompanyRepository;
import com.gsm.chwijuntime.repository.MemberRepository;
import com.gsm.chwijuntime.repository.TagRepository;
import com.gsm.chwijuntime.repository.tag.ContractingCompanyTagRepository;
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
public class ContractingCompanyServiceImpl implements ContractingCompanyService {

    private final ContractingCompanyRepository contractingCompanyRepository;
    private final ContractingCompanyTagRepository contractingCompanyTagRepository;
    private final MemberRepository memberRepository;
    private final TagRepository tagRepository;
    private final ModelMapper mapper;
    private final GetUserEmailUtil getUserEmailUtil;

    @Transactional
    @Override
    public void insertContractingCompany(ContractingCompanySaveDto contractingCompanySaveDto) {
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        ContractingCompany findBy = contractingCompanyRepository.findByContractingCompanyName(contractingCompanySaveDto.getContractingCompanyName());
        if(findBy == null) {
            contractingCompanyRepository.save(contractingCompanySaveDto.ToEntityByContractingCompany(member));
            for (String i : contractingCompanySaveDto.getTagName()) {
                Tag tag = tagRepository.findByTagName(i);
                if(tag == null) {
                    throw new NotFoundTagException();
                }
                ContractingCompany contractingCompany = contractingCompanyRepository.findByContractingCompanyName(contractingCompanySaveDto.getContractingCompanyName());
                contractingCompanySaveDto.MappingTag_ContractingCompany(tag, contractingCompany);
                contractingCompanyTagRepository.save(contractingCompanySaveDto.ToEntityByContractingCompanyTag());
            }
        } else {
            throw new DuplicateContractingCompanyException();
        }
    }

    @Override
    public List<ContractingCompanyResDto> findAllContractingCompany() {
        List<ContractingCompanyResDto> contractingCompanyResDtos = contractingCompanyRepository.findAll().stream()
                .map(m -> mapper.map(m, ContractingCompanyResDto.class))
                .collect(Collectors.toList());
        return getContractingCompanyResDtos(contractingCompanyResDtos);
    }

    @Override
    public ContractingCompanyResDto findByContractingCompanyIdx(Long idx) {
        ContractingCompanyResDto contractingCompanyResDto = contractingCompanyRepository.findById(idx)
                .map(m -> mapper.map(m, ContractingCompanyResDto.class)).orElseThrow(NotFoundContractingCompanyException::new);
        ContractingCompany contractingCompany = contractingCompanyRepository.findById(idx).orElseThrow(NotFoundContractingCompanyException::new);
        List<ContractingCompanyTag> allByContractingCompany = contractingCompanyTagRepository.findAllByContractingCompany(contractingCompany);
        for (ContractingCompanyTag i : allByContractingCompany) {
            contractingCompanyResDto.getContractingCompanyTags().add(i.getTag().getTagName());
        }
        return contractingCompanyResDto;
    }

    @Transactional
    @Override
    public void deleteContractingCompanyIdx(Long idx) {
        ContractingCompany contractingCompany = contractingCompanyRepository.findById(idx).orElseThrow(NotFoundContractingCompanyException::new);
        UserWriteCheck(contractingCompany);
        contractingCompanyTagRepository.deleteAllByContractingCompany(contractingCompany);
        contractingCompanyRepository.delete(contractingCompany);
    }

    @Transactional
    @Override
    public void updateContractingCompany(Long idx, ContractionCompanyUpdateDto contractionCompanyUpdateDto) {
        ContractingCompany contractingCompany = contractingCompanyRepository.findById(idx).orElseThrow(NotFoundContractingCompanyException::new);
        UserWriteCheck(contractingCompany);
        // 회사 이름 중복 판별
        if(contractingCompany.getContractingCompanyName().equals(contractionCompanyUpdateDto.getContractingCompanyName())) {
            throw new DuplicateContractingCompanyException();
        }
        // 1번째 수정
        contractingCompany.changeContractingCompany(contractionCompanyUpdateDto);
        // 관련된 태그 전체 지움
        List<ContractingCompanyTag> allByContractingCompany = contractingCompanyTagRepository.findAllByContractingCompany(contractingCompany);
        for (ContractingCompanyTag contractingCompanyTag : allByContractingCompany) {
            contractingCompanyTagRepository.delete(contractingCompanyTag);
        }
        // 태그 저장
        for (String i : contractionCompanyUpdateDto.getTagName()) {
            Tag tag = tagRepository.findByTagName(i);
            if(tag == null) {
                throw new NotFoundTagException();
            }
            contractionCompanyUpdateDto.MappingTag_ContractingCompany(tag, contractingCompany);
            contractingCompanyTagRepository.save(contractionCompanyUpdateDto.ToEntityByContractingCompanyTag());
        }
    }

    @Override
    public List<ContractingCompanyResDto> findByContractingBusinessAreasORContractingCompanyName(String keyword) {
        List<ContractingCompanyResDto> contractingCompanyResDtos = contractingCompanyRepository.searchByContractingBusinessAreasORContractingCompanyNameLike(keyword).stream()
                .map(m -> mapper.map(m, ContractingCompanyResDto.class))
                .collect(Collectors.toList());
        return getContractingCompanyResDtos(contractingCompanyResDtos);
    }

    private List<ContractingCompanyResDto> getContractingCompanyResDtos(List<ContractingCompanyResDto> contractingCompanyResDtos) {
        for (ContractingCompanyResDto i : contractingCompanyResDtos) {
            ContractingCompany contractingCompany = contractingCompanyRepository.findById(i.getContractingCompanyIdx()).orElseThrow(NotFoundContractingCompanyException::new);
            List<ContractingCompanyTag> contractingCompanyTags = contractingCompanyTagRepository.findAllByContractingCompany(contractingCompany);
            for (ContractingCompanyTag j : contractingCompanyTags) {
                i.getContractingCompanyTags().add(j.getTag().getTagName());
            }
        }
        return contractingCompanyResDtos;
    }

    //작성자 권한 체크 Method
    public void UserWriteCheck(ContractingCompany contractingCompany) {
        Member CurrentUser = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        Member WriteMember = contractingCompanyRepository.findById(contractingCompany.getContractingCompanyIdx()).orElseThrow(CAuthenticationEntryPointException::new).getMember();
        if (!CurrentUser.getMemberEmail().equals(WriteMember.getMemberEmail())) {
            throw new AuthorNotCertifiedException();
        }
    }
}
