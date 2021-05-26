package com.gsm.chwijuntime.model.tagmapping;

import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MemberTagIdx;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TagIdx")
    private Tag tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MemberIdx")
    private Member member;

    public void updateTag(Tag tag) {
        this.tag = tag;
    }

    public void clear() {

    }
}
