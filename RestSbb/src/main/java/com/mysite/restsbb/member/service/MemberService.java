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
                .modifyDate(LocalDateTime.now())
                .password(passwordEncoder.encode(password))
                .email(email)
                .nickname(nickname)
                .build();

        memberRepository.save(member);
        return RsData.of("200", "%s님, 가입에 성공하셨습니다.".formatted(nickname), member);
    }

    public Optional<Member> findById(Long id){
        return memberRepository.findById(id);
    }

    public Long count(){
        return memberRepository.count();
    }
}
