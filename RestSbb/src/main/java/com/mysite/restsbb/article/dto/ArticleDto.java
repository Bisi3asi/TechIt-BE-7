package com.mysite.restsbb.article.dto;

import com.mysite.restsbb.article.entity.Article;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleDto {
    private final Long id;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;
    private final Long authorId;
    private final String authorName;
    private final String title;
    private final String body;

    @Builder
    public ArticleDto(Article article) {
        this.id = article.getId();
        this.createDate = article.getCreateDate();
        this.modifyDate = article.getModifyDate();
        this.authorId = article.getAuthor().getId();
        this.authorName = article.getAuthor().getNickname();
        this.title = article.getTitle();
        this.body = article.getBody();
    }
}
