package com.gsm.chwijuntime.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notice {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeIdx;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 50000)
    private String content;

    private LocalDateTime createDated;

    // =================== 외래키(연관관계 주인) ================== //
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MemberIdx")
    private Member member;

    // =================== 비지니스 로직 ======================== //
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
