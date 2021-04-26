package com.gsm.chwijuntime.dto.portfolio;

import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.MemberPortfolio;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PortfolioSaveDto {

    private String notionPortfolioURL;

    public MemberPortfolio toEntityByPortfolio(Member member){
        return MemberPortfolio.builder()
                .notionPortfolioURL(this.getNotionPortfolioURL())
                .member(member)
                .build();
    }
}
