package com.gsm.chwijuntime.service.consultingadmin;

import com.gsm.chwijuntime.dto.consultingadmin.ConsultingAdminResDto;
import com.gsm.chwijuntime.dto.consultingadmin.ConsultingAdminSaveDto;
import com.gsm.chwijuntime.model.ConsultingAdmin;

import java.util.List;

public interface ConsultingAdminService {
    void saveConsultingAdmin(ConsultingAdminSaveDto consultingAdminSaveDto);
    ConsultingAdminResDto findByIdx(Long idx);
    List<ConsultingAdminResDto> findAll();
    void deleteConsultingAdmin(Long idx);
}
