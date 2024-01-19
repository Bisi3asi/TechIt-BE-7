package com.example.sbrest.global.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@ConfigurationProperties(prefix = "hidden.jwt")
public class JwtUtilProperties {

	private final String SECRET_KEY;
}
