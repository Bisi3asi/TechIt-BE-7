package com.example.springbootdemo.domain.article.article.controller;

import com.example.springbootdemo.domain.article.article.entity.Article;
import com.example.springbootdemo.domain.article.article.service.ArticleService;
import com.example.springbootdemo.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor // 생성자 주입
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/write")
    String showWrite() {
        return "write";
    }

    @PostMapping("/write")
    @ResponseBody
    RsData<Article> doWrite(String title, String body) {
        Article article = articleService.write(title, body);
        RsData<Article> rs = new RsData(
                "S-1",
                "%d번 게시물이 작성되었습니다.".formatted(article.getId()),
                article
        );

        return rs;
    }

    @GetMapping("/getLastArticle")
    @ResponseBody
    Article getLastArticle() {
        return articleService.findLastArticle();
    }

    @GetMapping("/getArticles")
    @ResponseBody
    List<Article> getArticles() {
        return articleService.findAll();
    }
}
