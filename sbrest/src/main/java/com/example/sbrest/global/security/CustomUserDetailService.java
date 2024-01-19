package com.example.sbrest.global.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sbrest.domain.users.entity.Users;
import com.example.sbrest.domain.users.service.UsersService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
	private final UsersService usersService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = usersService.findByUsername(username);

		return new SecurityUser(
			users.getId(),
			users.getUsername(),
			users.getPassword(),
			users.getGrantedAuthorities()
		);
	}
}

