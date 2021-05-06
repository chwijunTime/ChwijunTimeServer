package com.gsm.chwijuntime.service.consultinguser;

import com.gsm.chwijuntime.dto.consultinguser.ConsultingUserResDto;
import com.gsm.chwijuntime.dto.consultinguser.ConsultingUserSaveDto;
import com.gsm.chwijuntime.model.ConsultingUser;

import java.util.List;

public interface ConsultingUserService {
    void saveConsultingUser(Long idx, ConsultingUserSaveDto consultingUserSaveDto);
    List<ConsultingUserResDto> findAll();
}
