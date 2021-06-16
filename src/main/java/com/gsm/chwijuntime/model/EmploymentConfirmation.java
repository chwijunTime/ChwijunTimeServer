package com.gsm.chwijuntime.model;

import com.gsm.chwijuntime.dto.employmentconfirmation.EmploymentConfirmationUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 채용 확정 현황 테이블
public class EmploymentConfirmation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employmentConfirmationIdx;

    @Column(nullable = false)
    private String employmentConfirmationName;  //회사 이름

    @Column(nullable = false)
    private String employmentConfirmationAreas;  //회사 지역

    @Column(nullable = false)
    private String studentName;  //학생 이름 김OO

    @Column(nullable = false)
    private String employmentConfirmationJockey;  //기수

    @Column(nullable = false)
    private String employmentConfirmationAddress;  //회사 위치

    private String employmentConfirmationSite; //회사 사이트

    @Column(nullable = false, length = 2000)
    private String employmentConfirmationEtc;  //기타 정보

    // =============== 외래키(연관관계 주인) ================= //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MemberIdx")
    private Member member;

    public void changeEmploymentConfirmation(EmploymentConfirmationUpdateDto employmentConfirmationUpdateDto){
        this.studentName = employmentConfirmationUpdateDto.getStudentName();
        this.employmentConfirmationName = employmentConfirmationUpdateDto.getEmploymentConfirmationName();
        this.employmentConfirmationAreas = employmentConfirmationUpdateDto.getEmploymentConfirmationAreas();
        this.employmentConfirmationAddress = employmentConfirmationUpdateDto.getEmploymentConfirmationAddress();
        this.employmentConfirmationSite = employmentConfirmationUpdateDto.getEmploymentConfirmationSite();
        this.employmentConfirmationEtc = employmentConfirmationUpdateDto.getEmploymentConfirmationEtc();
    }
}
