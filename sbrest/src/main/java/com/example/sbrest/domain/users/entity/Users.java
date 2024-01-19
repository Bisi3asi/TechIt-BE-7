package com.example.sbrest.domain.users.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(callSuper = true)
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 20, unique = true)
	private String username;

	@Column(nullable = false, length = 20)
	private String nickname;

	@Column(nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Builder.Default
	private Set<Role> authorities = new HashSet<>();

	@Setter
	@Column(columnDefinition = "TEXT")
	private String refreshToken;

	public List<? extends GrantedAuthority> getGrantedAuthorities() {
		return getGrantedAuthoritiesAsStrList().stream()
			.map(SimpleGrantedAuthority::new)
			.toList();
	}

	public List<String> getGrantedAuthoritiesAsStrList() {
		return authorities.stream()
			.map(Role::getValue)
			.toList();
	}
}
