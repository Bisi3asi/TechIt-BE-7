package com.example.sbrest.domain.article.controller;

import static org.springframework.http.MediaType.*;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sbrest.domain.article.dto.ArticleRequestDto;
import com.example.sbrest.domain.article.dto.ArticleResponseDto;
import com.example.sbrest.domain.article.service.ArticleService;
import com.example.sbrest.domain.users.service.UsersService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/articles", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@Tag(name = "ArticleRestController", description = "게시글 컨트롤러 API")
public class ArticleRestController {
	private final ArticleService articleService;
	private final UsersService usersService;

	@GetMapping("")
	public ResponseEntity<Page<ArticleResponseDto>> getArticles(@RequestParam(defaultValue = "0") int page) {
		return ResponseEntity.ok(articleService.getList(page, 10));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ArticleResponseDto> getArticle(@PathVariable Long id) {
		return ResponseEntity.ok(articleService.get(id));
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("")
	public ResponseEntity write(@RequestBody @Valid ArticleRequestDto articleRequestDto, BindingResult brs, Principal principal) {
		if (brs.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(brs.getAllErrors());
		}
		return ResponseEntity.ok(
			articleService.create(articleRequestDto, usersService.findByUsername(principal.getName()))
		);
	}
}

