package com.gsm.chwijuntime.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberPortfolio {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MemberPortfolioIdx;

    @Column(nullable = false)
    private String NotionPortfolioURL;

    // ============= 외래키(연관관계 주인) ============== //
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MemberIdx")
    private Member member;
}
