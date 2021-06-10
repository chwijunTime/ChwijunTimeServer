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

    private String classNumber;

    @Column(length = 1000)
    private String correctionContent;

    @Column(length = 1000)
    private String reasonForRejection;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "correctionApplyIdx")
    private CorrectionApply correctionApply;
}
