package com.gsm.chwijuntime.service.portfolio;

import com.gsm.chwijuntime.dto.portfolio.PortfolioSaveDto;
import com.gsm.chwijuntime.dto.portfolio.PortfolioUpdateDto;
import com.gsm.chwijuntime.model.MemberPortfolio;

import java.util.List;

public interface PortfolioService {
    void savePortfolio(PortfolioSaveDto portfolioSaveDto);
    List<MemberPortfolio> findAll();
    MemberPortfolio findByIdx(Long idz);
    void updatePortfolio(Long idx, PortfolioUpdateDto portfolioUpdateDto);
    void deletePortfolio(Long idx);
    List<MemberPortfolio> myPortfolio();
}
