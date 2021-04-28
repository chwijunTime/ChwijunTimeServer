package com.gsm.chwijuntime.dto.portfolio;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PortfolioUpdateDto {

    @NotBlank(message = "포트폴리오 URL을 입력해주세요.")
    private String notionPortfolioURL;

}
