package com.gsm.chwijuntime.repository;

import com.gsm.chwijuntime.dto.consultingadmin.ConsultingAdminResDto;
import com.gsm.chwijuntime.model.ConsultingAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultingAdminRepository extends JpaRepository<ConsultingAdmin, Long> {

}
