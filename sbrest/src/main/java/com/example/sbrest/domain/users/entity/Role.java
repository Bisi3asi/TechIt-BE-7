package com.example.sbrest.domain.users.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

	USER("ROLE_USER");

	private final String value;
}
