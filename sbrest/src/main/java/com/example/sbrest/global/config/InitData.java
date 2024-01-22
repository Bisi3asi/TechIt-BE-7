package com.example.sbrest.global.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import com.example.sbrest.domain.article.dto.ArticleRequestDto;
import com.example.sbrest.domain.article.service.ArticleService;
import com.example.sbrest.domain.users.dto.UsersJoinRequestDto;
import com.example.sbrest.domain.users.entity.Users;
import com.example.sbrest.domain.users.service.UsersService;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Configuration
@RequiredArgsConstructor
public class InitData {
	private final ArticleService articleService;
	private final UsersService usersService;

	@Profile("!dev")
	@Bean
	public ApplicationRunner init() {
		return new ApplicationRunner() {
			@Override
			@Transactional
			@SneakyThrows
			public void run(ApplicationArguments args) {
				usersService.create(new UsersJoinRequestDto(
					"restuser1",
					"restuser1",
					"12345678",
					"12345678"
				));
				Users user1 = usersService.findByUsername("restuser1");

				for (int i = 1; i < 30; i++) {
					articleService.create(new ArticleRequestDto(
							String.format("테스트 글 %d", i),
							String.format("테스트 내용 %d", i)
						), user1
					);
				}
			}
		};
	}
}
