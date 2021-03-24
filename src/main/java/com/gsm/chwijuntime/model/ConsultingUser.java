package com.gsm.chwijuntime.model;

import com.gsm.chwijuntime.model.tagmapping.ConsultingUserTag;
import com.gsm.chwijuntime.model.tagmapping.TipsStorageTag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultingUser {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ConsultingUserIdx;

    @Column(nullable = false)
    private String ConsultingUserName;

    @Column(nullable = false)
    private String ConsultingUserClassNumber;

    // ================== 외래키(연관관계 주인) ==================== //
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ConsultingIdx")
    private ConsultingAdmin consultingAdmin;

    // ============== 태그 매핑(연관관계 노예) =================== //
    @OneToMany(mappedBy = "consultingUser", fetch = FetchType.LAZY)
    private List<ConsultingUserTag> consultingUserTags = new ArrayList<>();
}
