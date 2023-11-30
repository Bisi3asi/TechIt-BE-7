package com.mysite.restsbb.article.controller;

import com.mysite.restsbb.article.ArticleService;
import com.mysite.restsbb.article.dto.ArticleDto;
import com.mysite.restsbb.article.entity.Article;
import com.mysite.restsbb.global.rsdata.RsData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ApiV1ArticleController {
    private final ArticleService articleService;

    @Getter
    public static class GetArticlesResponseBody {
        private final List<ArticleDto> items;
        private final Map pagination;

        public GetArticlesResponseBody(List<Article> articles){
            items = articles.stream()
                    .map(ArticleDto::new)
                    .toList();
            pagination = Map.of("page", 1);
        }
    }
    @GetMapping("")
    public RsData<GetArticlesResponseBody> getArticles(){
        return RsData.of("200", "success",
                new GetArticlesResponseBody(articleService.findAllByOrderByIdDesc()));
    }
}
