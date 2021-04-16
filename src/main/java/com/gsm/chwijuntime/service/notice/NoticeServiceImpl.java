package com.gsm.chwijuntime.service.notice;

import com.gsm.chwijuntime.advice.exception.NotFoundNoticeException;
import com.gsm.chwijuntime.dto.notice.NoticeSaveDto;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.Notice;
import com.gsm.chwijuntime.repository.MemberRepository;
import com.gsm.chwijuntime.repository.NoticeRepository;
import com.gsm.chwijuntime.util.GetUserEmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final GetUserEmailUtil getUserEmailUtil;
    private final MemberRepository memberRepository;

    @Override
    public void save(NoticeSaveDto noticeSaveDto) {
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.GetUserEmail()).orElseThrow(NotFoundNoticeException::new);
        noticeRepository.save(noticeSaveDto.ToEntityByNotice(member));
    }

    @Override
    public Notice findById(Long idx) {
        Notice notice = noticeRepository.findById(idx).orElseThrow(NotFoundNoticeException::new);
        return notice;
    }

    @Override
    public List<Notice> findAll() {
        List<Notice> notices = noticeRepository.findAll();
        return notices;
    }

    @Transactional
    @Override
    public void deleteById(Long idx) {
        noticeRepository.deleteById(idx);
    }

    @Transactional
    @Override
    public void updateId(Long idx, NoticeSaveDto noticeSaveDto) {
        Notice notice = noticeRepository.findById(idx).orElseThrow(NotFoundNoticeException::new);
        notice.update(noticeSaveDto.getTitle(), noticeSaveDto.getContent());
    }
}
