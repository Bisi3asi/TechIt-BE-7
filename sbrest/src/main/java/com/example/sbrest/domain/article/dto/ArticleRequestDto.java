package com.example.sbrest.domain.article.dto;

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
public class ArticleRequestDto {
	@NotBlank(message = "제목은 공백일 수 없습니다.")
	@Size(max = 50, message = "제목은 50자를 초과할 수 없습니다.")
	private String title;

	@NotBlank(message = "내용은 공백일 수 없습니다.")
	@Size(max = 1000, message = "내용은 1000자를 초과할 수 없습니다.")
	private String content;
}
