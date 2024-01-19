package com.example.sbrest.domain.users.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersLoginRequestDto {
	private String username;
	private String password;
}
