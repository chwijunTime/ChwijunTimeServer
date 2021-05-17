package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.CompanyReview;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.TipsStorage;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipsStorageRepository extends JpaRepository<TipsStorage, Long> {

    List<TipsStorage> findAllByWorkCompanyName(String name);

    @Query("select t from TipsStorage t where t.workCompanyName like %:keyword% order by t.tipsStorageIdx")
    List<TipsStorage> searchByWorkCompanyName(@Param("keyword") String keyword);

    @Query("select t from TipsStorage t join fetch t.member where t.member = :member order by t.tipsStorageIdx")
    List<TipsStorage> findByMember(Member member);

}
