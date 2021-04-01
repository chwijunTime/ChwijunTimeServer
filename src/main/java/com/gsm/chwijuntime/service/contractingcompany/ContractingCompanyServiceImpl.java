package com.gsm.chwijuntime.service.contractingcompany;

import com.gsm.chwijuntime.dto.contractingcompany.ContractingCompanyResDto;
import com.gsm.chwijuntime.dto.contractingcompany.ContractingCompanySaveDto;
import com.gsm.chwijuntime.model.ContractingCompany;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.repository.ContractingCompanyRepository;
import com.gsm.chwijuntime.repository.MemberRepository;
import com.gsm.chwijuntime.repository.TagRepository;
import com.gsm.chwijuntime.repository.tag.ContractingCompanyTagRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ContractingCompanyServiceImpl implements ContractingCompanyService {

    private final ContractingCompanyRepository contractingCompanyRepository;
    private final ContractingCompanyTagRepository contractingCompanyTagRepository;
    private final MemberRepository memberRepository;
    private final TagRepository tagRepository;
    private final ModelMapper mapper;

    @Override
    public void insertContractingCompany(ContractingCompanySaveDto contractingCompanySaveDto) {
        Member member = memberRepository.findByMemberEmail(GetUserEmail()).orElseThrow(null);
        contractingCompanyRepository.save(contractingCompanySaveDto.ToEntityByContractingCompany(member));
        for (String i: contractingCompanySaveDto.getTagName()) {
            Tag tag = tagRepository.findByTagName(i);
            ContractingCompany contractingCompany = contractingCompanyRepository.findByContractingCompanyName(contractingCompanySaveDto.getContractingCompanyName());
            contractingCompanySaveDto.MappingTag_ContractingCompany(tag, contractingCompany);
            contractingCompanyTagRepository.save(contractingCompanySaveDto.ToEntityByContractingCompanyTag());
        }
    }

    @Override
    public List<ContractingCompanyResDto> findAllContractingCompany() {
        return contractingCompanyRepository.findAll().stream()
                .map(m -> mapper.map(m, ContractingCompanyResDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ContractingCompanyResDto findByContractingCompanyIdx(Long idx) {
        ContractingCompanyResDto contractingCompanyResDto = contractingCompanyRepository.findById(idx).map(m -> mapper.map(m, ContractingCompanyResDto.class)).orElseThrow(null);
        return contractingCompanyResDto;
    }

    @Override
    public void deleteContractingCompanyIdx(Long idx) {
        ContractingCompany contractingCompany = contractingCompanyRepository.findById(idx).orElseThrow(null);
        contractingCompanyRepository.delete(contractingCompany);
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
