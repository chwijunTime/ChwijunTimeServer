package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.ConsultingUser;
import com.gsm.chwijuntime.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultingUserRepository extends JpaRepository<ConsultingUser, Long> {

    @Query("select u from ConsultingUser u join fetch u.consultingAdmin")
    List<ConsultingUser> findAll();

    @Query("select u from ConsultingUser u join fetch u.consultingAdmin where u.member = :member")
    List<ConsultingUser> findByMember(Member member);
}
