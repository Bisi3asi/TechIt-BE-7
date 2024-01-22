package com.example.sbrest.domain.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "유저 회원가입 응답 DTO")
public class UsersJoinResponseDto {
	@Schema(description = "사용자 이름", defaultValue = "John Doe")
	private String nickname;

	@Schema(description = "응답 메시지", defaultValue = "회원가입 성공")
	private String msg;
}
