package com.example.sbrest.domain.users.controller;

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
import com.example.sbrest.domain.users.dto.UsersLoginRequestDto;
import com.example.sbrest.domain.users.dto.UsersLoginResponseDto;
import com.example.sbrest.domain.users.entity.Users;
import com.example.sbrest.domain.users.service.UsersService;
import com.example.sbrest.global.rq.Rq;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UsersRestController {
	private final UsersService usersService;
	private final Rq rq;

	@GetMapping("")
	public ResponseEntity showIsLogined(Principal principal){
		if (principal == null){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비로그인");
		}
		return ResponseEntity.ok(principal.getName());
	}

	@PostMapping("/join")
	public ResponseEntity join(@RequestBody UsersJoinRequestDto usersJoinRequestDto, BindingResult brs) {
		if (!usersJoinRequestDto.getPassword().contentEquals(usersJoinRequestDto.getPasswordConfirm())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호, 비밀번호 확인 불일치");
		}
		if (brs.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(brs.getAllErrors());
		}
		return usersService.create(usersJoinRequestDto);
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid UsersLoginRequestDto usersLoginRequestDto, BindingResult brs) {
		if (brs.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(brs.getAllErrors());
		}
		UsersLoginResponseDto usersLoginResponseDto = usersService.checkUsernameAndPassword(usersLoginRequestDto);

		Users users = usersService.findByUsername(usersLoginRequestDto.getUsername());
		usersService.setTokenWhenLogin(users);

		return ResponseEntity.ok(usersLoginResponseDto);
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/logout")
	public ResponseEntity logout(Principal principal) {
		if (principal == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("로그인된 상태가 아닙니다");
		}
		if (rq.getAccessTokenFromCookie(null) != null) {
			rq.removeAccessTokenFromCookie();
		}

		return ResponseEntity.ok("로그아웃되었습니다.");
	}
}
