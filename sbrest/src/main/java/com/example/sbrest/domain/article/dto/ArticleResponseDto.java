package com.example.sbrest.domain.article.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "게시글 작성 시 응답 DTO")
public class ArticleResponseDto {
	@Schema(description = "글 id", defaultValue = "999")
	private Long id;

	@Schema(description = "글 제목", defaultValue = "hello title")
	private String title;

	@Schema(description = "글 내용", defaultValue = "hello content")
	private String content;

	@Schema(description = "작성자 이름", defaultValue = "John Doe")
	private String authorName;
}
