package com.gsm.chwijuntime.service.requesttag;

import com.gsm.chwijuntime.dto.requesttag.RequestTagResDto;
import com.gsm.chwijuntime.dto.requesttag.RequestTagSaveDto;

import java.util.List;

public interface RequestTagService {
    void saveTag(RequestTagSaveDto requestTagSaveDto);
    void deleteTag(Long idx);
    List<RequestTagResDto> findAll();
    RequestTagResDto findByIdx(Long idx);
}
