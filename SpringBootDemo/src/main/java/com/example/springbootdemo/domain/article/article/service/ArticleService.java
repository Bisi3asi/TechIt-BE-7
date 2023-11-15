package com.example.springbootdemo.domain.article.article.service;

import com.example.springbootdemo.domain.article.article.entity.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleService {
    private final List<Article> articles = new ArrayList<>();

    public Article write(String title, String body){
        Article article = new Article(articles.size() + 1, title, body);
        articles.add(article);
        return article;
    }

    public List<Article> findAll() {
        return articles;
    }

    public Article findLastArticle() {
        // getLast는 java 21 버전에서만 지원
        return articles.getLast();
    }
}
