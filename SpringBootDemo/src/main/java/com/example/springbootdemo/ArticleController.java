package com.example.springbootdemo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {
    private List<Article> articles = new ArrayList<>();

    @GetMapping("/write")
    String showWrite() {
        return "write";
    }

    @PostMapping("/doWrite")
    @ResponseBody
    RsData<Article> doWrite(String title, String body) {
        Article article = new Article(articles.size() + 1, title, body);
        articles.add(article);

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
        // getLast는 java 21 버전에서만 지원
        return articles.getLast();
    }

    @GetMapping("/getArticles")
    @ResponseBody
    List<Article> getArticles() {
        return articles;
    }
}

@Getter
@AllArgsConstructor
class RsData<T> {
    private String resultCode;
    private String message;
    private T data;
}

@Getter
@AllArgsConstructor
class Article {
    private long id;
    private String title;
    private String body;
}
