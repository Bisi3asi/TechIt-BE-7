package com.example.sbrest.domain.article.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponseDto {
	private Long id;
	private String title;
	private String content;
	private String authorName;
}
