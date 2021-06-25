//package com.gsm.chwijuntime.security;
//
//import com.gsm.chwijuntime.handler.CustomAccessDeniedHandler;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//@Order(1)
//@Slf4j
//public class PrometheusWebConfig extends WebSecurityConfigurerAdapter {
//
//    private final PasswordEncoder passwordEncoder;
//
//    @Value("${spring.prometheus.name}")
//    private String USERNAME;
//
//    @Value("${spring.prometheus.password}")
//    private String PASSWORD;
//
//    @Value("${spring.prometheus.role}")
//    private String USER_ROLE;
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                .antMatcher("/actuator/**")
//                .authorizeRequests()
//                .anyRequest()
//                .hasRole(USER_ROLE)
//                .and()
//                .httpBasic();
//    }
//
//    @Override
//    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
//        authenticationManagerBuilder.inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder)
//                .withUser(USERNAME)
//                .password(passwordEncoder.encode(PASSWORD))
//                .roles(USER_ROLE);
//    }
//}
