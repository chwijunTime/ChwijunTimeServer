package com.gsm.chwijuntime.service.tag;

import com.gsm.chwijuntime.advice.exception.DuplicateTagNameException;
import com.gsm.chwijuntime.advice.exception.NotFoundTagException;
import com.gsm.chwijuntime.dto.tag.TagSaveDto;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Transactional
    @Override
    public void insertTag(TagSaveDto tagSaveDto) {
        tagSaveDto.setTagName(tagSaveDto.getTagName().toLowerCase(Locale.ROOT));
        Tag byTagName = tagRepository.findByTagName(tagSaveDto.getTagName());
        if(byTagName == null) {
            tagRepository.save(tagSaveDto.toEntity());
        } else {
            throw new DuplicateTagNameException();
        }
    }

    @Override
    public Tag findByTagIdxOne(Long tagIdx) {
        return tagRepository.findById(tagIdx).orElseThrow(NotFoundTagException::new);
    }

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Transactional
    @Override
    public Tag updateTag(Long tagIdx, String tagName) {
        Tag tag = tagRepository.findById(tagIdx).orElseThrow(NotFoundTagException::new);
        tag.changeTagName(tagName);
        return tag;
    }

    @Transactional
    @Override
    public void deleteTag(Long tagIdx) {
        Long tag = tagRepository.findById(tagIdx).orElseThrow(NotFoundTagException::new).getTagIdx();
        tagRepository.deleteById(tag);
    }
}
