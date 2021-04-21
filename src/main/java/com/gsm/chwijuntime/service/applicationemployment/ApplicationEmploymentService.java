package com.gsm.chwijuntime.service.applicationemployment;

import com.gsm.chwijuntime.dto.applicationemployment.ApplicationEmploymentSaveDto;
import com.gsm.chwijuntime.dto.applicationemployment.FindAllApplicationResDto;
import com.gsm.chwijuntime.model.ApplicationEmployment;

import java.util.List;

public interface ApplicationEmploymentService {

    // 공고 신청하기 => 사용자
    void application(Long employmentAnnouncementIdx, ApplicationEmploymentSaveDto applicationemploymentSaveDto);

    // 공고 신청 전체 보기 => 관리자 (학번, 이름, 깃허브, 회사 이름)
    List<FindAllApplicationResDto> findAllApplication();

    // 공고 요청 수락 하기 => 관리자
    void AcceptApplication();

    // 공고 요청 거절 하기 => 관리자
    void RejectApplication();

    // 공고 신청 자세히 보기 => 관리자 (전부 출력)
    ApplicationEmployment ApplicationDetail();

    // 신청 공고 전체 조회 => 관리자 (학번, 이름, 깃허브, 회사 이름)
    ApplicationEmployment findByAccept();

    // 거절 공고만 전체 조회 => 관리자 (학번, 이름, 깃허브, 회사 이름)
    ApplicationEmployment findByReject();

}
