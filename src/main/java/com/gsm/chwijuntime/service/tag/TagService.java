package com.gsm.chwijuntime.service.tag;

import com.gsm.chwijuntime.dto.tag.TagSaveDto;
import com.gsm.chwijuntime.model.Tag;

import java.util.List;

public interface TagService {

    void insertTag(TagSaveDto tagSaveDto);
    Tag findByTagIdxOne(Long tagIdx);
    List<Tag> findAll();
    void deleteTag(Long tagIdx);

}
