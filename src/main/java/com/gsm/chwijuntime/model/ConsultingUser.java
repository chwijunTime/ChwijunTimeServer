package com.gsm.chwijuntime.model;

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
// 상담 신청 테이블
public class ConsultingUser {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consultingUserIdx;

    @Column(nullable = false)
    private String consultingUserName;

    @Column(nullable = false)
    private String consultingUserClassNumber;

    // ================== 외래키(연관관계 주인) ==================== //
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ConsultingIdx")
    private ConsultingAdmin consultingAdmin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MemberIdx")
    private Member member;
}
