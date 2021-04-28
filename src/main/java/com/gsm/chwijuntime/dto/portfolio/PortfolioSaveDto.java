package com.gsm.chwijuntime.dto.portfolio;

import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.MemberPortfolio;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PortfolioSaveDto {

    @NotBlank(message = "포트폴리오 URL을 입력해주세요.")
    private String notionPortfolioURL;

    public MemberPortfolio toEntityByPortfolio(Member member){
        return MemberPortfolio.builder()
                .notionPortfolioURL(this.getNotionPortfolioURL())
                .member(member)
                .build();
    }
}
