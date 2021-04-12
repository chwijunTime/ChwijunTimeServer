package com.gsm.chwijuntime.service.notice;

import com.gsm.chwijuntime.dto.notice.NoticeSaveDto;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.Notice;
import com.gsm.chwijuntime.repository.MemberRepository;
import com.gsm.chwijuntime.repository.NoticeRepository;
import com.gsm.chwijuntime.util.GetUserEmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final GetUserEmailUtil getUserEmailUtil;
    private final MemberRepository memberRepository;


    @Override
    public void save(NoticeSaveDto noticeSaveDto) {
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.GetUserEmail()).orElseThrow(null);
        noticeRepository.save(noticeSaveDto.ToEntityByNotice(member));
    }

    @Override
    public Notice findById(Long idx) {
        return null;
    }

    @Override
    public List<Notice> findAll() {
        return null;
    }
}
