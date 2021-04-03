package com.gsm.chwijuntime.model.tagmapping;

import com.gsm.chwijuntime.model.EmploymentConfirmation;
import com.gsm.chwijuntime.model.MemberPortfolio;
import com.gsm.chwijuntime.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberPortfolioTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MemberPortfolioTagIdx;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TagIdx")
    private Tag tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MemberPortfolioIdx")
    private MemberPortfolio memberPortfolio;

}
