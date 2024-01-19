package com.example.sbrest.global.jwt;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.sbrest.domain.users.entity.Users;
import com.example.sbrest.domain.users.service.UsersService;
import com.example.sbrest.global.rq.Rq;
import com.example.sbrest.global.security.SecurityUser;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private final UsersService usersService;
	private final Rq rq;

	@Override
	@SneakyThrows
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) {
		String accessToken = rq.getAccessTokenFromCookie(null);
		String refreshToken = rq.getRefreshTokenFromCookie(null);

		if (accessToken != null && refreshToken != null) {
			SecurityUser user = usersService.getUserFromAccessToken(accessToken);
			if (user == null) { // user == null 일 때 : accessToken이 만료난 경우
				Users users = usersService.findByRefreshToken(refreshToken);
				// accessToken 재생성
				String newAccessToken = usersService.makeToken(member, 10);
				// 새로운 accessToken으로 SecurityUser 값 변경
				users = usersService.getUserFromAccessToken(newAccessToken);
				// newAccessToken으로 accessToken 값 변경
				rq.setAccessTokenToCookie(newAccessToken);
				System.out.println("accessToken refresh, update cookie success");
			}
			rq.setAuthentication(user);
		}

		filterChain.doFilter(req, resp);
	}
}
