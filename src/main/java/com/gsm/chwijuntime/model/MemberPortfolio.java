package com.gsm.chwijuntime.model;

import com.gsm.chwijuntime.model.tagmapping.MemberPortfolioTag;
import com.gsm.chwijuntime.model.tagmapping.TipsStorageTag;
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
public class MemberPortfolio {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberPortfolioIdx;

    @Column(nullable = false)
    private String notionPortfolioURL;

    // ============= 외래키(연관관계 주인) ============== //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MemberIdx")
    private Member member;

    public void changeNotionPortfolioURL(String url) {
        this.notionPortfolioURL = url;
    }
}
