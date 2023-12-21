package com.mysite.restsbb.member.controller;

import com.mysite.restsbb.global.rsdata.RsData;
import com.mysite.restsbb.global.util.jwt.JwtUtil;
import com.mysite.restsbb.member.dto.MemberDto;
import com.mysite.restsbb.member.entity.Member;
import com.mysite.restsbb.member.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class ApiV1MembersController {
    private final MemberService memberService;

    @Getter
    @Setter
    public static class LoginRequestBody {
        private String username;
        private String password;
    }

    @Getter
    public static class LoginResponseBody {
        private final MemberDto item;
        private final String accessToken;
        private final String refreshToken;

        public LoginResponseBody(Member member, String accessToken, String refreshToken) {
            item = new MemberDto(member);
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }
    }

    @PostMapping("/login")
    public RsData<LoginResponseBody> login(@RequestBody LoginRequestBody requestBody) {
        RsData<Member> checkRs = memberService.checkUsernameAndPassword(requestBody.getUsername(), requestBody.getPassword());
        Member member = checkRs.getData();

        Long id = member.getId();
        String accessToken = JwtUtil.encode(
                60 * 10, // jwt 토큰의 수명을 10분으로 단축
                Map.of("id", id.toString(),
                        "username", member.getUsername(),
                        "authorities", member.getAuthoritiesAsStrList()));

        String refreshToken = JwtUtil.encode(
                60 * 60 * 24 * 365,
                Map.of("id", id.toString(),
                        "username", member.getUsername()
                )
        );

        memberService.setRefreshToken(member, refreshToken);

        return RsData.of("200",
                "로그인 성공",
                new LoginResponseBody(member, accessToken, refreshToken));
    }

    @Setter
    @Getter
    public static class RefreshAccessTokenRequestBody {
        private String refreshToken;
    }

    @Getter
    public static class RefreshAccessTokenResponseBody {
        private final String accessToken;

        public RefreshAccessTokenResponseBody(String accessToken) {
            this.accessToken = accessToken;
        }
    }

    @PostMapping("/refreshAccessToken")
    public RsData<RefreshAccessTokenResponseBody> login(
            @RequestBody RefreshAccessTokenRequestBody requestBody
    ) {
        String refreshToken = requestBody.getRefreshToken();

        Member member = memberService.findByRefreshToken(refreshToken).get();

        Long id = member.getId();
        String accessToken = JwtUtil.encode(
                60 * 10,
                Map.of(
                        "id", id.toString(),
                        "username", member.getUsername(),
                        "authorities", member.getAuthoritiesAsStrList()
                )
        );

        return RsData.of(
                "200",
                "엑세스 토큰 재발급 성공",
                new RefreshAccessTokenResponseBody(accessToken)
        );
    }
}
