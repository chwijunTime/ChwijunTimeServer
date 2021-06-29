package com.gsm.chwijuntime.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@Slf4j
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(properties = "spring.config.location=" +
        "classpath:/application.yml"
)
@Transactional
@AutoConfigureMockMvc
public class NoticeControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void helloTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(equalTo("hello"))); //응답 받은 데이터를 String 비교
    }
}
