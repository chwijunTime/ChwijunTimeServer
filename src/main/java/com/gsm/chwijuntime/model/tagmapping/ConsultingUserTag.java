package com.gsm.chwijuntime.model.tagmapping;

import com.gsm.chwijuntime.model.CompanyReview;
import com.gsm.chwijuntime.model.ConsultingUser;
import com.gsm.chwijuntime.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultingUserTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ConsultingUserTagIdx;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "TagIdx")
    private Tag tag;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ConsultingUserIdx")
    private ConsultingUser consultingUser;

}
