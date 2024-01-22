package com.example.sbrest.domain.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "유저 회원가입 요청 DTO")
public class UsersJoinRequestDto {
	@Schema(description = "사용자 ID")
	@NotBlank(message = "ID를 입력해주세요.")
	@Size(min = 8, max = 20, message = "ID를 8 ~ 20자 이내로 입력해주세요.")
	private String username;

	@Schema(description = "사용자 이름")
	@NotBlank(message = "사용자 이름을 입력해주세요.")
	@Size(min = 2, max = 20, message = "사용자 이름을 2 ~ 20자 이내로 입력해주세요.")
	private String nickname;

	@Schema(description = "사용자 비밀번호")
	@NotBlank(message = "비밀번호를 입력해주세요.")
	@Size(min = 8, max = 20, message = "비밀번호를 8 ~ 20자 이내로 입력해주세요.")
	private String password;

	@Schema(description = "사용자 비밀번호 확인")
	@NotBlank(message = "비밀번호 확인을 입력해주세요.")
	@Size(min = 8, max = 20, message = "비밀번호 확인을 8 ~ 20자 이내로 입력해주세요.")
	private String passwordConfirm;
}
