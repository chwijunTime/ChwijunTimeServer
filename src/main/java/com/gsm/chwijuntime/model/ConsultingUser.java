package com.gsm.chwijuntime.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
// 상담 신청 테이블
public class ConsultingUser {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consultingUserIdx;

    @Column(nullable = false)
    private String consultingUserName;   // 신청자 이름

    @Column(nullable = false)
    private String consultingUserClassNumber;  //신청자 학번

    // ================== 외래키(연관관계 주인) ==================== //
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ConsultingIdx")
    private ConsultingAdmin consultingAdmin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MemberIdx")
    private Member member;
}
