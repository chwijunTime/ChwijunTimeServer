package com.gsm.chwijuntime.service.contractingcompany;

import com.gsm.chwijuntime.dto.contractingcompany.ContractingCompanyResDto;
import com.gsm.chwijuntime.dto.contractingcompany.ContractingCompanySaveDto;
import com.gsm.chwijuntime.model.ContractingCompany;

import java.util.List;

public interface ContractingCompanyService {
    void insertContractingCompany(ContractingCompanySaveDto contractingCompanySaveDto);
    List<ContractingCompanyResDto> findAllContractingCompany();
    ContractingCompanyResDto findByContractingCompanyIdx(Long idx);
    void deleteContractingCompanyIdx(Long idx);
}

