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
    private CorrectionStatus correctionStatus;

    @Enumerated(EnumType.STRING)
    private CorrectionType correctionType;

    @ManyToOne(fetch = FetchType.EAGER)
    private MemberPortfolio memberPortfolio;

    @ManyToOne(fetch = FetchType.EAGER)
    private MemberResume memberResume;

    @ManyToOne(fetch = FetchType.EAGER)
    private Member member;

    public void changeApproval(){
        this.correctionStatus = CorrectionStatus.Correction_Successful;
    }

    public void changeRejection(){
        this.correctionStatus = CorrectionStatus.Correction_Rejection;
    }
}
