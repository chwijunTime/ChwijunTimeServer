package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.MemberPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberPortfolioRepository extends JpaRepository<MemberPortfolio, Long> {

    @Query("select p from MemberPortfolio p join fetch p.member order by p.memberPortfolioIdx desc")
    List<MemberPortfolio> findAll();

    @Query("select p from MemberPortfolio p join fetch p.member where p.member = :member order by p.memberPortfolioIdx desc")
    List<MemberPortfolio> findByMember(Member member);

    @Query("select p from MemberPortfolio p join fetch p.member where p.memberPortfolioIdx = :idx")
    MemberPortfolio findByMemberPortfolioIdx(Long idx);
}
