package com.gsm.chwijuntime.service.consultingadmin;

import com.gsm.chwijuntime.dto.consultingadmin.ConsultingAdminResDto;
import com.gsm.chwijuntime.dto.consultingadmin.ConsultingAdminSaveDto;

import java.util.List;

public interface ConsultingAdminService {

    //관리자 권한 => 관리자가 저장
    void saveConsultingAdmin(ConsultingAdminSaveDto consultingAdminSaveDto);

    // 사용자 권한 => 사용자가 단일 검색
    ConsultingAdminResDto findByIdx(Long idx);

    // 사용자 권한 => 사용자가 전체 검색 (사용자가 날짜가 지나지 않은 것을 조회한다.)
    List<ConsultingAdminResDto> findAll();

    // 관리자 권한 => 관리자가 삭제
    void deleteConsultingAdmin(Long idx);
}
