package com.example.sbrest.global.security;

import static org.springframework.security.config.http.SessionCreationPolicy.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import com.example.sbrest.global.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class ApiSecurityConfig {
	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	@SneakyThrows
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.
			securityMatcher("/api/**")
			.authorizeRequests(
				authorizeRequests -> authorizeRequests
					.requestMatchers(HttpMethod.GET, "api/*/members/logout").authenticated()
					.requestMatchers(HttpMethod.POST, "api/*/articles").authenticated()
					.anyRequest().permitAll()
			)
			.csrf(AbstractHttpConfigurer::disable) // CSRF 보호 비활성화
			.sessionManagement(s ->
				s.sessionCreationPolicy(STATELESS)
					.sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::none)) // 세션 생성 비활성화
			.headers(
				headers ->
					headers.frameOptions(
						frameOptions ->
							frameOptions.sameOrigin()
					)
			)
			.formLogin(AbstractHttpConfigurer::disable) // 기본 로그인 비활성화
			.logout(AbstractHttpConfigurer::disable) // 기본 로그아웃 비활성화
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
