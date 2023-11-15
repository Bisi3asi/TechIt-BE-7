package com.example.springbootdemo.domain.article.article.service;

import com.example.springbootdemo.domain.article.article.entity.Article;
import com.example.springbootdemo.domain.article.article.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 저는 단 한번만 생성되고, 그 이후에는 재사용되는 객체입니다.
// Component와 동일
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Autowired // 필드에 생성자가 하나 밖에 없으면 생략 가능하다
    public ArticleService(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

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
