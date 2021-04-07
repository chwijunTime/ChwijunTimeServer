package com.gsm.chwijuntime.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
// 상담 등록 테이블
public class ConsultingAdmin {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ConsultingIdx;

    @Column(nullable = false)
    private LocalDateTime ApplicationDate;

    @Enumerated(EnumType.STRING)
    private ConsultingStatus consultingStatus;

    // ================ 외래키(연관관계 주인) ================= //
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MemberIdx")
    private Member member;
}
