package com.example.springbootdemo.domain.article.article.repository;

import com.example.springbootdemo.domain.article.article.entity.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    private final List<Article> articles = new ArrayList<>();

    public Article save(Article article){
        if (article.getId() == null){
            article.setId(articles.size() + 1L);
        }

        articles.add(article);

        return article;
    }

    public Article findAll(){
        return articles.getLast();
    }

    public List<Article> findLastArticle() {
        return articles;
    }
}
