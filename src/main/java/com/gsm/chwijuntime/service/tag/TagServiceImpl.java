package com.gsm.chwijuntime.service.tag;

import com.gsm.chwijuntime.advice.exception.DuplicateTagNameException;
import com.gsm.chwijuntime.advice.exception.NotFoundTagException;
import com.gsm.chwijuntime.dto.tag.TagSaveDto;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.repository.TagRepository;
import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Transactional
    @Override
    public void insertTag(TagSaveDto tagSaveDto) {
        if(isKorean(tagSaveDto.getTagName())) {
            //한국어
            Tag byTagName = tagRepository.findByTagName(tagSaveDto.getTagName());
            if(byTagName == null) {
                tagRepository.save(tagSaveDto.toEntity());
            } else {
                throw new DuplicateTagNameException();
            }
        } else {
            //영어
            tagSaveDto.setTagName(tagSaveDto.getTagName().toLowerCase(Locale.ROOT));
            Tag byTagName = tagRepository.findByTagName(tagSaveDto.getTagName());
            if(byTagName == null) {
                tagRepository.save(tagSaveDto.toEntity());
            } else {
                throw new DuplicateTagNameException();
            }
        }
    }

    // 한국어 검사기
    public boolean isKorean(String str) {
        return Pattern.matches("[가-힣]*$", str);
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
