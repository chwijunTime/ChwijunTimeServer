package com.gsm.chwijuntime.model;

import com.gsm.chwijuntime.dto.tipstorage.TipsStorageUpdateDto;
import com.gsm.chwijuntime.model.tagmapping.TipsStorageTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
// 꿀팁 테이블
public class TipsStorage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tipsStorageIdx;

    @Column(nullable = false)
    private String workCompanyName;

    @Column(nullable = false)
    private String workCompanyAddress;

    @Column(nullable = false)
    private String tipsInfo;

    // ============== 외래키(연관관계 주인) ================= //
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MemberIdx")
    private Member member;

    public void update(TipsStorageUpdateDto tipsStorageUpdateDto) {
        this.workCompanyName = tipsStorageUpdateDto.getWorkCompanyName();
        this.workCompanyAddress = tipsStorageUpdateDto.getWorkCompanyAddress();
        this.tipsInfo = tipsStorageUpdateDto.getTipsInfo();
    }
}
