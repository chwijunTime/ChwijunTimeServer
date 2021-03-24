package com.gsm.chwijuntime.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentConfirmation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long EmploymentConfirmationIdx;

    @Column(nullable = false)
    private String EmploymentConfirmationName;

    @Column(nullable = false)
    private String EmploymentConfirmationAreas;

    @Column(nullable = false)
    private String EmploymentConfirmationClassNumber;

    @Column(nullable = false)
    private String EmploymentConfirmationAddress;

    @Column(nullable = false)
    private String EmploymentConfirmationSite;

    @Column(nullable = false)
    private String EmploymentConfirmationEtc;

    // =============== 외래키(연관관계 주인) ================= //
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MemberIdx")
    private Member member;
}
