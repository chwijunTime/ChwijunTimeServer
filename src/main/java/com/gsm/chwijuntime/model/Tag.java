package com.gsm.chwijuntime.model;

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
public class Tag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long TagIdx;

    @Column(nullable = false)
    private String TagName;


    // ============== 태그 매핑(연관관계 노예) =================== //
    @OneToMany(mappedBy = "tag", fetch = FetchType.LAZY)
    private List<TipsStorageTag> tipsStorageTags = new ArrayList<>();


}
