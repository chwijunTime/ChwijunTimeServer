package com.gsm.chwijuntime.model.tagmapping;

import com.gsm.chwijuntime.model.CompanyReview;
import com.gsm.chwijuntime.model.EmploymentAnnouncement;
import com.gsm.chwijuntime.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyReviewTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CompanyReviewTagIdx;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "TagIdx")
    private Tag tag;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CompanyReviewIdx")
    private CompanyReview companyReview;

}
