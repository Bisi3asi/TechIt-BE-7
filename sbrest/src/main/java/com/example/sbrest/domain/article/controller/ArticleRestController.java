package com.example.sbrest.domain.article.controller;

import static org.springframework.http.MediaType.*;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

// todo : validation 관련 전역 exception 처리
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/articles", produces = APPLICATION_JSON_VALUE)
@Tag(name = "ArticleRestController", description = "게시글 컨트롤러 API")
public class ArticleRestController {
	private final ArticleService articleService;
	private final UsersService usersService;

	@Operation(summary = "게시물 전체조회", description = "page param에 따라 10건씩 조회")
	@GetMapping("")
	public ResponseEntity<Page<ArticleResponseDto>> getArticles(@RequestParam(defaultValue = "0") int page) {
		return ResponseEntity.ok(articleService.getList(page, 10));
	}

	@Operation(summary = "게시물 단건조회", description = "pathVariable id값으로 단건 조회", responses = {
		@ApiResponse(responseCode = "200", description = "게시글 조회 성공",
			content = @Content(schema = @Schema(implementation = ArticleResponseDto.class))
		)
	})
	@GetMapping("/{id}")
	public ResponseEntity<ArticleResponseDto> getArticle(@PathVariable Long id) {
		return ResponseEntity.ok(articleService.get(id));
	}

	@Operation(summary = "게시물 작성", description = "(Login Required) 제목과 내용으로 글 작성")
	@PreAuthorize("isAuthenticated()")
	@PostMapping("")
	public ResponseEntity<ArticleResponseDto> write(@RequestBody @Valid ArticleRequestDto articleRequestDto, Principal principal) {
		return ResponseEntity.ok(
			articleService.create(articleRequestDto, usersService.findByUsername(principal.getName()))
		);
	}
}

