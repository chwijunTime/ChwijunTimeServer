package com.gsm.chwijuntime.config;

import org.apache.commons.validator.routines.UrlValidator;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public UrlValidator urlValidator() {
        return new UrlValidator();
    }

}
