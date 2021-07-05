package com.gsm.chwijuntime.service;

import com.gsm.chwijuntime.advice.exception.NotFoundTagException;
import com.gsm.chwijuntime.dto.tag.TagSaveDto;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.service.tag.TagService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import static org.junit.Assert.fail;

@Slf4j
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(properties = "spring.config.location=" +
        "classpath:/application.yml"
)
@Transactional
public class TagServiceTest {

    @Autowired
    private TagService tagService;

    @Before
    public void 태그_저장() throws Exception {
        log.info("태그 저장 실행");
        TagSaveDto tagSaveDto = TagSaveDto.builder()
                .tagName("리액트")
                .build();
        tagService.insertTag(tagSaveDto);
    }

    @Test(expected = NotFoundTagException.class)
    public void 태그_조회() throws Exception {
        log.info("태그 조회 테스트");
        //given
        Long tagIdx = 1L;

        //when
        Tag tag = tagService.findByTagIdxOne(tagIdx);

        //then
        assertEquals(tag.getTagName(), "리액트");
    }

    @Test(expected = NotFoundTagException.class)
    public void 태그_없음() throws Exception {
        log.info("태그 없음 테스트");
        //given
        Long tagIdx = 2L;

        //when
        tagService.findByTagIdxOne(tagIdx);

        //then
        fail("예외가 발생해야 한다.");
    }


}
