package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.ConsultingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultingUserRepository extends JpaRepository<ConsultingUser, Long> {

}
