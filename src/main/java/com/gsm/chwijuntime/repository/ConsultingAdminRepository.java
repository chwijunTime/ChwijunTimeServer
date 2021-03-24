package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.model.ConsultingAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultingAdminRepository extends JpaRepository<ConsultingAdmin, Long> {

}
