package com.gsm.chwijuntime.model;

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
public class RequestTag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rTagIdx;

    @Column(nullable = false)
    private String rTagName;

    // =================== 외래키(연관관계 주인) ================== //
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MemberIdx")
    private Member member;

}
