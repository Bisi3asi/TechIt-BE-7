package com.mysite.restsbb.article.controller;

import com.mysite.restsbb.article.dto.ArticleDto;
import com.mysite.restsbb.article.entity.Article;
import com.mysite.restsbb.article.service.ArticleService;
import com.mysite.restsbb.global.rq.Rq;
import com.mysite.restsbb.global.rsdata.RsData;
import com.mysite.restsbb.member.entity.Member;
import com.mysite.restsbb.member.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ApiV1ArticlesController {
    private final ArticleService articleService;
    private final Rq rq;
    private final MemberService memberService;

    // GET (Articles)
    @Getter
    public static class GetArticlesResponseBody {
        private final List<ArticleDto> items;
        private final Map pagination;

        public GetArticlesResponseBody(List<Article> articles) {
            items = articles.stream()
                    .map(ArticleDto::new)
                    .toList();
            pagination = Map.of("page", 1);
        }
    }

    @GetMapping("")
    public RsData<GetArticlesResponseBody> getArticles() {
        return RsData.of("200", "success",
                new GetArticlesResponseBody(articleService.findAllByOrderByIdDesc()));
    }

    // GET (Article)
    @Getter
    public static class GetArticleResponseBody {
        private final ArticleDto item;

        public GetArticleResponseBody(Article article) {
            item = new ArticleDto(article);
        }
    }

    @GetMapping("/{id}")
    public RsData<GetArticleResponseBody> getArticle(@PathVariable long id) {
        return RsData.of("200", "success",
                new GetArticleResponseBody(articleService.findById(id).get()));
    }

    // DELETE (Article)
    @Getter
    public static class RemoveArticleResponseBody {
        private final ArticleDto item;

        public RemoveArticleResponseBody(Article article) {
            item = new ArticleDto(article);
        }
    }

    @DeleteMapping("/{id}")
    public RsData<RemoveArticleResponseBody> removeArticle(@PathVariable long id) {
        Article article = articleService.findById(id).get();
        articleService.deleteById(id);

        return RsData.of("200", "success",
                new RemoveArticleResponseBody(article));
    }

    // PUT (Article)
    @Getter
    @Setter
    public static class ModifyArticleRequestBody {
        private String title;
        private String body;
    }

    @Getter
    public static class ModifyArticleResponseBody {
        private final ArticleDto item;

        public ModifyArticleResponseBody(Article article) {
            item = new ArticleDto(article);
        }
    }

    @PutMapping("/{id}")
    public RsData<ModifyArticleResponseBody> modifyArticle(
            @PathVariable long id,
            @RequestBody ModifyArticleRequestBody body
    ) {
        Article article = articleService.findById(id).get();

        articleService.modify(article, body.getTitle(), body.getBody());

        return RsData.of(
                "200",
                "성공",
                new ModifyArticleResponseBody(
                        article
                )
        );
    }

    // post
    @Getter
    @Setter
    public static class WriteArticleRequestBody {
        private String title;
        private String body;
    }

    @Getter
    public static class WriteArticleResponseBody {
        private final ArticleDto item;

        public WriteArticleResponseBody(Article article) {
            item = new ArticleDto(article);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("")
    public RsData<WriteArticleResponseBody> writeArticle(
            @RequestBody WriteArticleRequestBody body
    ) {
        Member member = rq.getMember();
        Article article = articleService.write(member, body.getTitle(), body.getBody()).getData();

        return RsData.of(
                "200",
                "성공",
                new WriteArticleResponseBody(
                        article
                )
        );
    }
}
