package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.ContractingCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractingCompanyRepository extends JpaRepository<ContractingCompany, Long> {

    ContractingCompany findByContractingCompanyName(String name);

}
