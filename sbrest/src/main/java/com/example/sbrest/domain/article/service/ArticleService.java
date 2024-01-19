package com.example.sbrest.domain.article.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sbrest.domain.article.dto.ArticleRequestDto;
import com.example.sbrest.domain.article.dto.ArticleResponseDto;
import com.example.sbrest.domain.article.entity.Article;
import com.example.sbrest.domain.article.repository.ArticleRepository;
import com.example.sbrest.domain.users.entity.Users;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {
	private final ArticleRepository articleRepository;

	public Page<ArticleResponseDto> getList(int page, int pageSize) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("id"));

		Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sorts));
		Page<Article> articlePage = articleRepository.findAll(pageable);

		return articlePage.map(this::buildArticleResponseDto);
	}

	public ArticleResponseDto get(Long id){
		Article article = articleRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("No Article"));

		return buildArticleResponseDto(article);
	}

	@Transactional
	public ArticleResponseDto create(ArticleRequestDto articleRequestDto, Users users){

		Article article = Article.builder()
			.title(articleRequestDto.getTitle())
			.content(articleRequestDto.getContent())
			.users(users)
			.build();

		articleRepository.save(article);
		return buildArticleResponseDto(article);
	}

	private ArticleResponseDto buildArticleResponseDto(Article article) {
		return new ArticleResponseDto(
			article.getId(),
			article.getTitle(),
			article.getContent(),
			article.getUsers().getNickname()
		);
	}
}
