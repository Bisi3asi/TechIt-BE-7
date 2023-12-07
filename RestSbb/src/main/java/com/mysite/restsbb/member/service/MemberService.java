package com.mysite.restsbb.member.service;

import com.mysite.restsbb.global.rsdata.RsData;
import com.mysite.restsbb.member.entity.Member;
import com.mysite.restsbb.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Optional<Member> findByApiKey(String apiKey) {
        return memberRepository.findByApiKey(apiKey);
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
