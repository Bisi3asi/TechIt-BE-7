package com.example.springbootdemo.domain.article.article.service;

import com.example.springbootdemo.domain.article.article.entity.Article;
import com.example.springbootdemo.domain.article.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 저는 단 한번만 생성되고, 그 이후에는 재사용되는 객체입니다, Component와 동일
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

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
