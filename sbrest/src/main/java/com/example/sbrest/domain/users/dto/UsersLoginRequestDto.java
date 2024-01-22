package com.example.sbrest.domain.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "유저 로그인 요청 DTO")
public class UsersLoginRequestDto {
	@Schema(description = "사용자 ID")
	private String username;

	@Schema(description = "사용자 비밀번호")
	private String password;
}
