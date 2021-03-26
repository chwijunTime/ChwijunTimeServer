package com.gsm.chwijuntime.model;

import com.gsm.chwijuntime.model.tagmapping.ContractingCompanyTag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContractingCompany {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ContractingCompanyIdx;

    @Column(nullable = false)
    private String ContractingCompanyName;

    @Column(nullable = false)
    private String ContractingBusinessAreas;

    @Column(nullable = false)
    private String ContractingCompanyAddress;

    @Column(nullable = false)
    private String ContractingCompanyAboutUs;

    private String ContractingCompanyAverageAnnualSalary;

    // ================== 외래키(연관관계 주인) ================== //
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MemberIdx")
    private Member member;
}
