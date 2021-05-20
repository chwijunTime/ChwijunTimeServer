package com.gsm.chwijuntime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing
public class ChwijunTimeServerApplication {

    String PROPERTIES = "spring.config.location=classpath:/application.yml"
            +",classpath:/key.yml";

    public static void main(String[] args) {
        //SpringApplication.run(ChwijunTimeServerApplication.class, args);
        new SpringApplicationBuilder(ChwijunTimeServerApplication.class)
                .properties(PROPERTIES)
                .run(args);
    }

}
