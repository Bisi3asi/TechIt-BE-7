package com.example.sbrest.global.jwt;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtUtil {
	private final JwtUtilProperties jwtUtilProperties;

	public static String encodeToken(Map<String, Object> data, int minute, String SECRET_KEY){
		Date now = new Date();
		Date expiration = new Date(now.getTime() + 1000 * 60 * minute);

		Claims claims = Jwts.claims() // JWTmutator 사용
			.setSubject("medium")
			.add("type", "access_token")
			.add("data", data)
			.build();

		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(now)
			.setExpiration(expiration)
			.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
			.compact();
	}

	public static Claims decodeToken(String token, String SECRET_KEY) {
		try {
			return Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.build()
				.parseClaimsJws(token)
				.getPayload();
		} catch (JwtException e) {
			System.out.printf("%s : Token has Expired", token);
			return null;
		}
	}
}