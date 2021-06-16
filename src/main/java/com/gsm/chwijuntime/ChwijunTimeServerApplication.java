package com.gsm.chwijuntime;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
public class ChwijunTimeServerApplication {
    public static void main(String[] args) {
        String PROPERTIES = "spring.config.location=classpath:/application.yml";

        new SpringApplicationBuilder(ChwijunTimeServerApplication.class)
                .properties(PROPERTIES)
                .run(args);
    }
}