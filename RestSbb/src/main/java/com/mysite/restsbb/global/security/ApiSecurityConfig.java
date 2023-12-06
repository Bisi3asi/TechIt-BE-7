package com.mysite.restsbb.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class ApiSecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    // Get은 발생하지 않는 csrf 토큰 문제에 대해 Delete, Post는 발생
    // /api/로 시작하는 url에 대해 csrf를 disable
    SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http
                // securityMatcher(url) 이후로 나오는 모든 작업은 해당 패턴에 해당하는 요청에만 적용
                .securityMatcher("/api/**")
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }
}
