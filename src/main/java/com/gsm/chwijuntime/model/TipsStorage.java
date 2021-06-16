package com.gsm.chwijuntime.model;

import com.gsm.chwijuntime.dto.tipstorage.TipsStorageUpdateDto;
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
// 꿀팁 테이블
public class TipsStorage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tipsStorageIdx;

    @Column(nullable = false)
    private String workCompanyName;   // 자신이 다니는 회사 이름

    @Column(nullable = false)
    private String workCompanyAddress; // 자신이 다니는 회사 위치

    @Column(nullable = false, length = 40000)
    private String tipsInfo;   //꿀팁 정보

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
