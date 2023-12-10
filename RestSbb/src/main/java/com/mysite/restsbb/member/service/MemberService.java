package com.mysite.restsbb.member.service;

import com.mysite.restsbb.global.rsdata.RsData;
import com.mysite.restsbb.global.security.SecurityUser;
import com.mysite.restsbb.global.util.jwt.JwtUtil;
import com.mysite.restsbb.member.entity.Member;
import com.mysite.restsbb.member.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public RsData<Member> join(String username, String password, String email, String nickname) {
        Member member = Member.builder()
                .username(username)
                .modifyDate(LocalDateTime.now())
                .password(passwordEncoder.encode(password))
                .email(email)
                .nickname(nickname)
                .build();

        memberRepository.save(member);
        return RsData.of("200", "%s님, 가입에 성공하셨습니다.".formatted(nickname), member);
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public SecurityUser getUserFromApiKey(String apiKey) {
        Claims claims = JwtUtil.decode(apiKey);

        Map<String, Object> data = (Map<String, Object>) claims.get("data");
        long id = Long.parseLong((String) data.get("id"));
        String username = (String) data.get("username");
        List<? extends GrantedAuthority> authorities = ((List<String>) data.get("authorities")).stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

        // jwt 토큰을 써도 id 조회를 하고 있는데 이 부분은 개선을 할 수 있다.
        return new SecurityUser(id, username, "", authorities);
    }

    public RsData<Member> checkUsernameAndPassword(String username, String password) {
        Optional<Member> memberOp = findByUsername(username);

        if (memberOp.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }

        if (!passwordEncoder.matches(password, memberOp.get().getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return RsData.of("200", "로그인 성공", memberOp.get());
    }

    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    public Long count() {
        return memberRepository.count();
    }
}
