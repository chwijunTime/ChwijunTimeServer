package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.ContractingCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractingCompanyRepository extends JpaRepository<ContractingCompany, Long> {

    ContractingCompany findByContractingCompanyName(String name);

}
