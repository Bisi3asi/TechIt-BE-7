package com.mysite.restsbb.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        authorizeHttpRequests -> authorizeHttpRequests
                                .requestMatchers( // h2 console 예외 처리
                                        PathRequest.toStaticResources().atCommonLocations(),
                                        new AntPathRequestMatcher("/resources/**"),
                                        new AntPathRequestMatcher("/h2-console/**")
                                )
                                .permitAll()
                                .requestMatchers( // 그 외에는 로그인을 해라
                                        "/adm/**"
                                )
                                .hasRole("ADMIN")
                                .anyRequest()
                                .permitAll()
                )
                .headers(
                        headers -> headers
                                .addHeaderWriter(
                                        new XFrameOptionsHeaderWriter(
                                                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)
                                )
                )
                .csrf(
                        csrf -> csrf
                                .ignoringRequestMatchers(
                                        "/h2-console/**"
                                )

                )
                .formLogin(
                        formLogin -> formLogin
                                .loginPage("/member/login")
                )
                .logout(
                        logout -> logout
                                .logoutRequestMatcher(
                                        new AntPathRequestMatcher("/member/logout")
                                )
                )
        ;
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
