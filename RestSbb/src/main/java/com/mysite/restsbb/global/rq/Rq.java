package com.mysite.restsbb.global.rq;


import com.mysite.restsbb.member.entity.Member;
import com.mysite.restsbb.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@RequiredArgsConstructor
public class Rq {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final MemberService memberService;
    private Member member;

    public Member getMember() {
        if (member == null) {
            // filter에서 적용한 가짜 유저를 가져온다
            User user = (User) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            user.getUsername();
            long memberId = Long.parseLong(user.getUsername());

            member = memberService.findById(memberId).get();
        }

        return member;
    }
}