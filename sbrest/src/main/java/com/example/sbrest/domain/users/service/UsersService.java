package com.example.sbrest.domain.users.service;

import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.sbrest.domain.users.dto.UsersJoinRequestDto;
import com.example.sbrest.domain.users.dto.UsersLoginRequestDto;
import com.example.sbrest.domain.users.dto.UsersLoginResponseDto;
import com.example.sbrest.domain.users.entity.Role;
import com.example.sbrest.domain.users.entity.Users;
import com.example.sbrest.domain.users.repository.UsersRepository;
import com.example.sbrest.global.jwt.JwtUtil;
import com.example.sbrest.global.jwt.JwtUtilProperties;
import com.example.sbrest.global.rq.Rq;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsersService {
	private final JwtUtilProperties jwtUtilProperties;
	private final UsersRepository usersRepository;
	private final PasswordEncoder passwordEncoder;
	private final Rq rq;

	public Users findByUsername(String username) {
		return usersRepository.findByUsername(username).orElseThrow(
			() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR : 해당 회원을 찾을 수 없습니다.")
		);
	}

	public Users findByRefreshToken(String refreshToken) {
		return usersRepository.findByRefreshToken(refreshToken)
			.orElseThrow(() -> {
				rq.removeRefreshTokenFromCookie();
				rq.removeAccessTokenFromCookie();
				return new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR: 해당 회원을 찾을 수 없습니다.");
			});
	}

	public UsersLoginResponseDto checkUsernameAndPassword(UsersLoginRequestDto usersLoginRequestDto) {
		Users users = usersRepository.findByUsername(usersLoginRequestDto.getUsername()).orElseThrow(
			() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR : 해당 회원을 찾을 수 없습니다.")
		);

		if (!passwordEncoder.matches(usersLoginRequestDto.getPassword(), users.getPassword())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR : 로그인 실패, 비밀번호를 확인해주세요.");
		}

		return new UsersLoginResponseDto(users.getUsername(), users.getAuthorities().toString());
	}

	@Transactional
	public void create(UsersJoinRequestDto usersJoinRequestDto) {
		if (usersRepository.findByUsername(usersJoinRequestDto.getUsername()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR : 회원가입 실패, 중복된 아이디가 있습니다.");
		}

		Users users = Users.builder()
			.username(usersJoinRequestDto.getUsername())
			.nickname(usersJoinRequestDto.getNickname())
			.password(passwordEncoder.encode(usersJoinRequestDto.getPassword()))
			.authorities(Set.of(Role.USER))
			.build();
		usersRepository.save(users);
	}


	@Transactional
	public void setTokenWhenLogin(Users users) {
		String accessToken = makeToken(users, 5);
		String refreshToken = makeToken(users, 60 * 24 * 7);
		setRefreshToken(users, refreshToken);

		rq.setAccessTokenToCookie(accessToken);
		rq.setRefreshTokenToCookie(refreshToken);
	}

	@Transactional
	public void setRefreshToken(Users users, String refreshToken) {
		users.setRefreshToken(refreshToken);
		usersRepository.save(users);
	}

	public String makeToken(Users users, int minute) {
		return JwtUtil.encodeToken(
			Map.of(
				"id", users.getId().toString(),
				"username", users.getUsername(),
				"authorities", users.getGrantedAuthoritiesAsStrList()
			), minute, jwtUtilProperties.getSECRET_KEY());
	}
}
