package com.mysite.restsbb.global.rq;


import com.mysite.restsbb.global.security.SecurityUser;
import com.mysite.restsbb.member.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@RequiredArgsConstructor
public class Rq {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final EntityManager entityManager;
    private Member member;
    private SecurityUser securityUser;

    public boolean isAdmin() {
        if (isLogout()) return false;

        return getSecurityUser()
                .getAuthorities()
                .stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    }

    public boolean isLogin() {
        return getSecurityUser() != null;
    }

    public boolean isLogout() {
        return !isLogin();
    }

    public SecurityUser getSecurityUser() {
        if (securityUser == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) return null;
            Object principal = authentication.getPrincipal();
            if (principal == null) return null;
            securityUser = (SecurityUser) principal;
        }

        return securityUser;
    }

    public Member getMember() {
        if (isLogout()) return null;
        if (member == null) {
            member = entityManager.getReference(Member.class, getSecurityUser().getId());
            // 프록시 객체를 생성, new Member(memberId)와 같다.
            // fetchType = Lazy가 걸려 있는 회원 엔티티 객체를 리턴해 회원 Select를 피한다.
        }

        return member;
    }

    public String getHeader(String name, String defaultValue){
        String value = request.getHeader(name);
        if (value == null){
            return defaultValue;
        }
        return null;
    }

    // JwtAuthenticationFilter에서 해당 user의 principal과 권한 설정하는 기능을 리팩토링
    public void setAuthentication(SecurityUser user){
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user,
                user.getPassword(),
                user.getAuthorities());

        // Security 상에서 getContext.setAuthentication으로 Principal 사용 가능
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}