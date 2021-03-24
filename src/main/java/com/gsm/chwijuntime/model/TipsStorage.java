package com.gsm.chwijuntime.model;

import com.gsm.chwijuntime.model.tagmapping.TipsStorageTag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    // ============== 태그 매핑(연관관계 노예) =================== //
    @OneToMany(mappedBy = "tipsStorage", fetch = FetchType.LAZY)
    private List<TipsStorageTag> tipsStorageTags = new ArrayList<>();
}
