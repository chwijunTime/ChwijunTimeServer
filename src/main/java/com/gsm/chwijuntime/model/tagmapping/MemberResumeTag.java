package com.gsm.chwijuntime.model.tagmapping;

import com.gsm.chwijuntime.model.CompanyReview;
import com.gsm.chwijuntime.model.MemberResume;
import com.gsm.chwijuntime.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResumeTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MemberResumeTagIdx;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TagIdx")
    private Tag tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MemberResumeIdx")
    private MemberResume memberResume;

}
