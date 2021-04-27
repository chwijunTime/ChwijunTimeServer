package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.MemberResume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberResumeRepository extends JpaRepository<MemberResume, Long> {

    @Query("select r from MemberResume r join fetch r.member where r.member = :member")
    List<MemberResume> findAllByMember(Member member);

    @Query("select r from MemberResume r join fetch r.member where r.memberResumeIdx = :idx")
    MemberResume findByMemberResumeIdx(Long idx);

    @Query("select r from MemberResume r join fetch r.member")
    List<MemberResume> findAll();
}
