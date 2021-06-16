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
    private String title;  //제목

    @Column(nullable = false, length = 40000)
    private String content;  //내용

    private LocalDateTime createDated;  // 작성 날짜

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
