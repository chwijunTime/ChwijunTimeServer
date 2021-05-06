package com.gsm.chwijuntime.service.notice;

import com.gsm.chwijuntime.dto.notice.NoticeSaveDto;
import com.gsm.chwijuntime.model.Notice;

import java.util.List;

public interface NoticeService {
    void save(NoticeSaveDto noticeSaveDto);
    Notice findById(Long idx);
    List<Notice> findAll();
    void deleteById(Long idx);
    void updateId(Long idx, NoticeSaveDto noticeSaveDto);
}
