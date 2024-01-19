package com.example.sbrest.domain.article.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArticleResponseDto {
	private String title;
	private String body;
	private String authorName;
}
