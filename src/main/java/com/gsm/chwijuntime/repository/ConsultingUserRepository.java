package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.ConsultingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultingUserRepository extends JpaRepository<ConsultingUser, Long> {

    @Query("select u from ConsultingUser u join fetch u.consultingAdmin")
    List<ConsultingUser> findAll();

}
