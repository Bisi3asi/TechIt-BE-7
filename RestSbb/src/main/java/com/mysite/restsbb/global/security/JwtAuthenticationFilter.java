package com.mysite.restsbb.global.security;

import com.mysite.restsbb.global.rq.Rq;
import com.mysite.restsbb.member.service.MemberService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final MemberService memberService;
    private final Rq rq;

    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        String apiKey = rq.getHeader("X-ApiKey", null);

        if (apiKey != null) {
            SecurityUser user = memberService.getUserFromApiKey(apiKey);
            // setAuthentication : principal 객체 설정
            rq.setAuthentication(user);
        }

        filterChain.doFilter(request, response);
    }
}
