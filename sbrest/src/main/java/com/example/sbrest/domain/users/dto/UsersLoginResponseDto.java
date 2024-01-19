package com.example.sbrest.domain.users.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersLoginResponseDto {
	private String nickname;
	private String role;
}
