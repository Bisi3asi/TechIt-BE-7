package com.mysite.restsbb.article;

import com.mysite.restsbb.article.entity.Article;
import com.mysite.restsbb.article.repository.ArticleRepository;
import com.mysite.restsbb.global.rsdata.RsData;
import com.mysite.restsbb.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional
    public RsData<Article> write(Member author, String title, String body) {
        Article article = Article.builder()
                .author(author)
                .title(title)
                .body(body)
                .build();

        articleRepository.save(article);
        return RsData.of("200", "%d번 게시글이 작성되었습니다".formatted(article.getId()), article);
    }

    public Optional<Article> findById(long id){
        return articleRepository.findById(id);
    }

    public RsData<Article> modify(Article article, String title, String body){
        article = article.toBuilder()
                .title(title)
                .body(body)
                .build();
        articleRepository.save(article);

        return RsData.of("200", "%번 게시글이 수정되었습니다.".formatted(article.getId()), article);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public List<Article> findAllByOrderByIdDesc() {
        return articleRepository.findAllByOrderByIdDesc();
    }
}
