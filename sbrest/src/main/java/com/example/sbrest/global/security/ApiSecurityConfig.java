package com.example.sbrest.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
	public SecurityFilterChain filterChain(HttpSecurity http) {
		http.
			authorizeRequests(
				authorizeRequests -> authorizeRequests
					.requestMatchers("/h2-console/**").permitAll()
					.requestMatchers("/actuator/**").permitAll()
			)
			.securityMatcher("/api/**")
			.authorizeRequests(
				authorizeRequests -> authorizeRequests
					.requestMatchers(HttpMethod.GET, "api/*/members/logout").authenticated()
					.requestMatchers(HttpMethod.POST, "api/*/articles").authenticated()
					.anyRequest().permitAll()
			)
			.csrf(AbstractHttpConfigurer::disable) // CSRF 보호 비활성화
			.sessionManagement(AbstractHttpConfigurer::disable) // session 아예 꺼버림
			.cors(cors -> cors.configure(http)) // cors 설정 활성화
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
