package com.mysite.restsbb.global.security;

import com.mysite.restsbb.member.entity.Member;
import com.mysite.restsbb.member.service.MemberService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final MemberService memberService;
    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {

        // parameter로 username을 받는다.
        String username = request.getParameter("username");

        // parameter의 username이 null이 아니면 username으로 id, pw를 조회해 로그인 정보를 넘긴다.
        if (username != null) {
            Member member = memberService.findByUsername(username).get();

            User user = new User(
                    member.getUsername(),
                    member.getPassword(),
                    List.of()
            );

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    user,
                    user.getPassword(),
                    user.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(auth);

            filterChain.doFilter(request, response);
            return;
        }

        // username 정보가 파라미터로 안넘어오면 user1로 넘긴다.
        User user = new User("user1", "", List.of());

        Authentication auth = new UsernamePasswordAuthenticationToken(
                // filter에 가짜 유저를 넣어서 로그인 된 것처럼 처리
                user,
                user.getPassword(),
                user.getAuthorities()
        );

        // Security 상에서 getContext.setAuthentication으로 Principal 사용 가능
        SecurityContextHolder.getContext().setAuthentication(auth);

        // filter 이후 시점부터는 로그인 된 것으로 간주
        filterChain.doFilter(request, response);
    }
}
