package com.example.sbrest.domain.users.dto;

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
public class UsersJoinRequestDto {
	@NotBlank(message = "ID를 입력해주세요.")
	@Size(min = 8, max = 20, message = "ID를 8 ~ 20자 이내로 입력해주세요.")
	private String username;

	@NotBlank(message = "사용자 이름을 입력해주세요.")
	@Size(min = 2, max = 20, message = "사용자 이름을 2 ~ 20자 이내로 입력해주세요.")
	private String nickname;

	@NotBlank(message = "비밀번호를 입력해주세요.")
	@Size(min = 8, max = 20, message = "비밀번호를 8 ~ 20자 이내로 입력해주세요.")
	private String password;

	@NotBlank(message = "비밀번호 확인을 입력해주세요.")
	@Size(min = 8, max = 20, message = "비밀번호 확인을 8 ~ 20자 이내로 입력해주세요.")
	private String passwordConfirm;
}
