package com.gsm.chwijuntime.service.contractingcompany;

import com.gsm.chwijuntime.dto.contractingcompany.ContractingCompanyResDto;
import com.gsm.chwijuntime.dto.contractingcompany.ContractingCompanySaveDto;
import com.gsm.chwijuntime.dto.contractingcompany.ContractionCompanyUpdateDto;

import java.util.List;

public interface ContractingCompanyService {
    void insertContractingCompany(ContractingCompanySaveDto contractingCompanySaveDto);
    List<ContractingCompanyResDto> findAllContractingCompany();
    ContractingCompanyResDto findByContractingCompanyIdx(Long idx);
    void deleteContractingCompanyIdx(Long idx);
    void updateContractingCompany(Long idx, ContractionCompanyUpdateDto contractionCompanyUpdateDto);

    //지역 OR 회사 이름 검색
    List<ContractingCompanyResDto> findByContractingBusinessAreasORContractingCompanyName(String keyword);
}

