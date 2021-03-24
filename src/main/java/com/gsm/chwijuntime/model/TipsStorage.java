package com.gsm.chwijuntime.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TipsStorage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long TipsStorageIdx;

    @Column(nullable = false)
    private String WorkCompanyName;

    @Column(nullable = false)
    private String WorkCompanyAddress;

    @Column(nullable = false)
    private String TipsInfo;

    // ============== 외래키(연관관계 주인) ================= //
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MemberIdx")
    private Member member;
}
