package com.gsm.chwijuntime.model;

import com.gsm.chwijuntime.model.tagmapping.MemberPortfolioTag;
import com.gsm.chwijuntime.model.tagmapping.TipsStorageTag;
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
public class MemberPortfolio {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MemberPortfolioIdx;

    @Column(nullable = false)
    private String NotionPortfolioURL;

    // ============= 외래키(연관관계 주인) ============== //
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MemberIdx")
    private Member member;

    // ============== 태그 매핑(연관관계 노예) =================== //
    @OneToMany(mappedBy = "memberPortfolio", fetch = FetchType.LAZY)
    private List<MemberPortfolioTag> memberPortfolioTags = new ArrayList<>();
}
