package com.gsm.chwijuntime.service.applicationemployment;

import com.gsm.chwijuntime.dto.applicationemployment.ApplicationEmploymentSaveDto;
import com.gsm.chwijuntime.dto.applicationemployment.FindAllApplicationDetailResDto;
import com.gsm.chwijuntime.dto.applicationemployment.FindAllApplicationResDto;
import com.gsm.chwijuntime.model.ApplicationEmployment;

import java.util.List;

public interface ApplicationEmploymentService {

    // 공고 신청하기 => 사용자
    void application(Long employmentAnnouncementIdx, ApplicationEmploymentSaveDto applicationemploymentSaveDto);

    // 공고 요청 수락 하기 => 관리자
    void acceptApplication(Long idx) throws Exception;

    // 공고 요청 거절 하기 => 관리자
    void rejectApplication(Long idx) throws Exception;

    // 공고 신청 자세히 보기 => 관리자 (전부 출력)
    FindAllApplicationDetailResDto applicationDetail(Long idx);

    // Body로 대기, 거절, 승인 보내서 조회하기 => (학번, 이름, 깃허브, 회사 이름)
    List<FindAllApplicationResDto> findByStatus(String status);
}
