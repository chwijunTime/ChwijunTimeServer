package com.gsm.chwijuntime.repository.tag;

import com.gsm.chwijuntime.model.tagmapping.ContractingCompanyTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractingCompanyTagRepository extends JpaRepository<ContractingCompanyTag, Long> {

}
