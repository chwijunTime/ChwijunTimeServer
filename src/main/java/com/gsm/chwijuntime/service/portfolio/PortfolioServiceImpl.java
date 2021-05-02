package com.gsm.chwijuntime.service.portfolio;

import com.gsm.chwijuntime.dto.portfolio.PortfolioSaveDto;
import com.gsm.chwijuntime.dto.portfolio.PortfolioUpdateDto;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.MemberPortfolio;
import com.gsm.chwijuntime.repository.MemberPortfolioRepository;
import com.gsm.chwijuntime.repository.MemberRepository;
import com.gsm.chwijuntime.util.GetUserEmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PortfolioServiceImpl implements PortfolioService {

    private final GetUserEmailUtil getUserEmailUtil;
    private final MemberRepository memberRepository;
    private final MemberPortfolioRepository memberPortfolioRepository;

    @Transactional
    @Override
    public void savePortfolio(PortfolioSaveDto portfolioSaveDto) {
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(null);
        memberPortfolioRepository.save(portfolioSaveDto.toEntityByPortfolio(member));
    }

    @Override
    public List<MemberPortfolio> findAll() {
        return memberPortfolioRepository.findAll();
    }

    @Override
    public MemberPortfolio findByIdx(Long idx) {
        return memberPortfolioRepository.findByMemberPortfolioIdx(idx);
    }

    @Transactional
    @Override
    public void updatePortfolio(Long idx, PortfolioUpdateDto portfolioUpdateDto) {
        MemberPortfolio memberPortfolio = memberPortfolioRepository.findById(idx).orElseThrow(null);
        memberPortfolio.changeNotionPortfolioURL(portfolioUpdateDto.getNotionPortfolioURL());
    }

    @Transactional
    @Override
    public void deletePortfolio(Long idx) {
        memberPortfolioRepository.deleteById(idx);
    }

    @Override
    public List<MemberPortfolio> myPortfolio() {
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(null);
        return memberPortfolioRepository.findByMember(member);
    }
}
