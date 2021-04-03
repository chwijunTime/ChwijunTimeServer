package com.gsm.chwijuntime.repository.tag;

import com.gsm.chwijuntime.model.ContractingCompany;
import com.gsm.chwijuntime.model.tagmapping.ContractingCompanyTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractingCompanyTagRepository extends JpaRepository<ContractingCompanyTag, Long> {

    List<ContractingCompanyTag> findAllByContractingCompany(ContractingCompany contractingCompany);

}
