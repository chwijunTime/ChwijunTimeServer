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

    @Column(nullable = false)
    private String classNumber;  //첨삭 학번

    @Column(length = 65000)
    private String correctionContent;  //첨삭 내용

    @Column(length = 65000)
    private String reasonForRejection;  //거절 이유

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "correctionApplyIdx")
    private CorrectionApply correctionApply;
}
