package com.gsm.chwijuntime.service.tag;

import com.gsm.chwijuntime.dto.tag.TagSaveDto;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;


    @Override
    public void insertTag(TagSaveDto tagSaveDto) {
        tagRepository.save(tagSaveDto.toEntity());
    }

    @Override
    public Tag findByTagIdxOne(Long tagIdx) {
        return tagRepository.findById(tagIdx).orElseThrow(null);
    }

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public void deleteTag(Long tagIdx) {
        Tag tag = tagRepository.findById(tagIdx).orElseThrow(null);
        tagRepository.delete(tag);
    }
}
