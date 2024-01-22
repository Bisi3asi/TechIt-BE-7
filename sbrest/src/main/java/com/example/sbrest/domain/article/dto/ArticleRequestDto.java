package com.example.sbrest.domain.article.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "게시글 작성 시 요청 DTO")
public class ArticleRequestDto {
	@Schema(description = "게시글 제목")
	@NotBlank(message = "제목은 공백일 수 없습니다.")
	@Size(max = 50, message = "제목은 50자를 초과할 수 없습니다.")
	private String title;

	@Schema(description = "게시글 내용")
	@NotBlank(message = "내용은 공백일 수 없습니다.")
	@Size(max = 1000, message = "내용은 1000자를 초과할 수 없습니다.")
	private String content;
}
