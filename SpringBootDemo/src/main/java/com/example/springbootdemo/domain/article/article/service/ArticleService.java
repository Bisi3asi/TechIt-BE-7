package com.example.springbootdemo.domain.article.article.service;

import com.example.springbootdemo.domain.article.article.entity.Article;
import com.example.springbootdemo.domain.article.article.repository.ArticleRepository;

import java.util.ArrayList;
import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepository();

    public Article write(String title, String body){
        Article article = new Article(title, body);
        articleRepository.save(article);
        return article;
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findLastArticle() {
        // getLast는 java 21 버전에서만 지원
        return articleRepository.findLastArticle();
    }
}
