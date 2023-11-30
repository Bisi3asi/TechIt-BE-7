package com.mysite.restsbb.article.controller;

import com.mysite.restsbb.article.ArticleService;
import com.mysite.restsbb.article.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ApiV1ArticleController {
    private final ArticleService articleService;

    @GetMapping("")
    public List<ArticleDto> getArticles(){
        return articleService.findAll().stream()
                .map(ArticleDto::new)
                .toList();
    }
}
