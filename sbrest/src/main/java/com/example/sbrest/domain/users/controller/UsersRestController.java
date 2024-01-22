package com.example.sbrest.domain.users.controller;

import static org.springframework.http.MediaType.*;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sbrest.domain.users.dto.UsersJoinRequestDto;
import com.example.sbrest.domain.users.dto.UsersJoinResponseDto;
import com.example.sbrest.domain.users.dto.UsersLoginRequestDto;
import com.example.sbrest.domain.users.dto.UsersLoginResponseDto;
import com.example.sbrest.domain.users.dto.UsersLogoutResponseDto;
import com.example.sbrest.domain.users.entity.Users;
import com.example.sbrest.domain.users.service.UsersService;
import com.example.sbrest.global.rq.Rq;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

// todo : validation 관련 전역 exception 처리
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@Tag(name = "UsersRestController", description = "유저 컨트롤러 API")
public class UsersRestController {
	private final Rq rq;
	private final UsersService usersService;

	@Operation(summary = "로그인 상태 조회", description = "(Login Required) 로그인된 사용자 ID를 리턴합니다.")
	@GetMapping("")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<UsersLoginResponseDto> showIsLogined(Principal principal){
		if (principal == null){
			return ResponseEntity.badRequest().body(new UsersLoginResponseDto(null, "로그인되어 있지 않습니다."));
		}
		Users users = usersService.findByUsername(principal.getName());

		return ResponseEntity.ok(new UsersLoginResponseDto(users.getNickname(), users.getAuthorities().toString()));
	}

	@Operation(summary = "회원가입", description = "회원가입을 합니다.")
	@PostMapping("/join")
	public ResponseEntity<UsersJoinResponseDto> join(@RequestBody @Valid UsersJoinRequestDto usersJoinRequestDto) {
		if (!usersJoinRequestDto.getPassword().contentEquals(usersJoinRequestDto.getPasswordConfirm())) {
			return ResponseEntity.badRequest().body(new UsersJoinResponseDto(null, "패스워드가 일치하지 않습니다"));
		}
		return usersService.create(usersJoinRequestDto);
	}

	@Operation(summary = "로그인", description = "로그인을 합니다.")
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/login")
	public ResponseEntity<UsersLoginResponseDto> login(@RequestBody @Valid UsersLoginRequestDto usersLoginRequestDto) {
		UsersLoginResponseDto usersLoginResponseDto = usersService.checkUsernameAndPassword(usersLoginRequestDto);

		Users users = usersService.findByUsername(usersLoginRequestDto.getUsername());
		usersService.setTokenWhenLogin(users);

		return ResponseEntity.ok(usersLoginResponseDto);
	}

	@Operation(summary = "로그아웃", description = "(Login Required) 로그인을 합니다.")
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/logout")
	public ResponseEntity<UsersLogoutResponseDto> logout(Principal principal) {
		Users users = usersService.findByUsername(principal.getName());

		if (rq.getAccessTokenFromCookie(null) != null) {
			rq.removeAccessTokenFromCookie();
		}

		return ResponseEntity.ok(new UsersLogoutResponseDto(users.getNickname(), "로그아웃 성공"));
	}
}
