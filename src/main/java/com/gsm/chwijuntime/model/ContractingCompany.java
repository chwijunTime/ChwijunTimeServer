package com.gsm.chwijuntime.model;

import com.gsm.chwijuntime.model.tagmapping.ContractingCompanyTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractingCompany {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractingCompanyIdx;

    @Column(nullable = false)
    private String contractingCompanyName;

    @Column(nullable = false)
    private String contractingBusinessAreas;

    @Column(nullable = false)
    private String contractingCompanyAddress;

    @Column(nullable = false)
    private String contractingCompanyAboutUs;

    private String contractingCompanyAverageAnnualSalary;

    // ================== 외래키(연관관계 주인) ================== //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MemberIdx")
    private Member member;



}
