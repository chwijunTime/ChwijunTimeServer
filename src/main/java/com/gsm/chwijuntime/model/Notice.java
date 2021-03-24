package com.gsm.chwijuntime.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Notice {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long NoticeIdx;

    @Column(nullable = false)
    private String Title;

    @Column(nullable = false)
    private String Content;

    private LocalDateTime CreateDated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MemberIdx")
    private Member member;
}
