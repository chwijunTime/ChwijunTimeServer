package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.ContractingCompany;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractingCompanyRepository extends JpaRepository<ContractingCompany, Long> {

    ContractingCompany findByContractingCompanyName(String name);

    @Query("select c from ContractingCompany c join fetch c.member order by c.contractingCompanyIdx desc")
    List<ContractingCompany> findAll();

    @Query("SELECT c FROM ContractingCompany c join fetch c.member where c.contractingBusinessAreas like %:keyword% or c.contractingCompanyName like %:keyword% order by c.contractingCompanyIdx desc")
    List<ContractingCompany> searchByContractingBusinessAreasORContractingCompanyNameLike(@Param("keyword") String keyword);
}
