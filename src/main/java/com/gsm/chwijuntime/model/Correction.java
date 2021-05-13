package com.gsm.chwijuntime.model;

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
// 첨삭 테이블
public class Correction {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long correctionIdx;

    private String correctionContent;

    private String reasonForRejection;

    @ManyToOne(fetch = FetchType.LAZY)
    private CorrectionApply correctionApply;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MemberIdx")
    private Member member;
}
