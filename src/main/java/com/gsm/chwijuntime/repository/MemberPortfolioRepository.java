package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.MemberPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberPortfolioRepository extends JpaRepository<MemberPortfolio, Long> {

}
