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
    private String employmentConfirmationName;

    @Column(nullable = false)
    private String employmentConfirmationAreas;

    @Column(nullable = false)
    private String studentName;

    @Column(nullable = false)
    private String employmentConfirmationJockey;

    @Column(nullable = false)
    private String employmentConfirmationAddress;

    @Column(nullable = false)
    private String employmentConfirmationSite;

    @Column(nullable = false)
    private String employmentConfirmationEtc;

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
