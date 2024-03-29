package com.example.sbrest.domain.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "유저 로그인 응답 DTO")
public class UsersLoginResponseDto {
	@Schema(description = "사용자 이름", defaultValue = "John Doe")
	private String nickname;

	@Schema(description = "사용자 권한", defaultValue = "USER")
	private String role;
}
