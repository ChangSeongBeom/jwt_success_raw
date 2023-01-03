package com.example.realjwtnew.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        CorsConfiguration config= new CorsConfiguration();

        config.setAllowCredentials(true);//프론트에서 자바스크립트 문구를 처리할 수 있게 처리
        config.addAllowedMethod("*");//ip
        config.addAllowedHeader("*");//header에 응답 허용
        config.addAllowedOrigin("*");//method 허용

        source.registerCorsConfiguration("/api/**",config);
        return new CorsFilter(source);
    }
}
