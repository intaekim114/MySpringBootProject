package com.basic.myspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
    @Bean
    public CustomerVO customerVO() {
        return CustomerVO.builder() // 내부적으로 CustomerVOBuilder 가 생성
                .mode("테스트 환경")
                .rate(0.5)
                .build();
    }
}
