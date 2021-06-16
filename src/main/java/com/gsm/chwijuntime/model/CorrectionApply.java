package com.gsm.chwijuntime.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
// 첨삭 신청 테이블
public class CorrectionApply {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long correctionApplyIdx;

    @Enumerated(EnumType.STRING)
    private CorrectionStatus correctionStatus;  // 첨삭 상태

    @Enumerated(EnumType.STRING)
    private CorrectionType correctionType;  //첨삭 타입

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "memberPortfolioIdx")
    private MemberPortfolio memberPortfolio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "memberResumeIdx")
    private MemberResume memberResume;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "memberIdx")
    private Member member;

    public void changeApproval(){
        this.correctionStatus = CorrectionStatus.Correction_Successful;
    }

    public void changeRejection(){
        this.correctionStatus = CorrectionStatus.Correction_Rejection;
    }
}
