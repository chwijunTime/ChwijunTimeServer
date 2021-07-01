package com.gsm.chwijuntime;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
@EnableJpaAuditing
@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
@EnableCaching
public class ChwijunTimeServerApplication {
    public static void main(String[] args) {
        String PROPERTIES = "spring.config.location=classpath:/application.yml";

        new SpringApplicationBuilder(ChwijunTimeServerApplication.class)
                .properties(PROPERTIES)
                .run(args);
    }

    // PutMapping, DeleteMapping을 사용하기 위해 Bean 주입
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }
}