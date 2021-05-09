package com.gsm.chwijuntime.service.consultinguser;

import com.gsm.chwijuntime.dto.consultinguser.ConsultingUserResDto;
import com.gsm.chwijuntime.dto.consultinguser.ConsultingUserSaveDto;
import com.gsm.chwijuntime.model.ConsultingUser;

import java.util.List;

public interface ConsultingUserService {

    // 상담 신청
    void saveConsultingUser(Long idx, ConsultingUserSaveDto consultingUserSaveDto);

    // 관리자가 신청 조회 => 신청한 상담을 조회한다.
    List<ConsultingUserResDto> findAll();
}
