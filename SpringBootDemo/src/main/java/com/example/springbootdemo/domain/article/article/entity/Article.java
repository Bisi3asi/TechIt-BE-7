package com.example.springbootdemo.domain.article.article.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Article {
    private Long id;
    private String title;
    private String body;

    public Article(String title, String body){
        this.title = title;
        this.body = body;
    }
}
