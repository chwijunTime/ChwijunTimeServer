package com.gsm.chwijuntime.model.tagmapping;

import com.gsm.chwijuntime.model.ContractingCompany;
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
public class ContractingCompanyTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ContractingCompanyTagIdx;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TagIdx")
    private Tag tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ContractingCompanyIdx")
    private ContractingCompany contractingCompany;

}
