package com.mysite.restsbb.global.initdata;

import com.mysite.restsbb.article.ArticleService;
import com.mysite.restsbb.article.entity.Article;
import com.mysite.restsbb.member.entity.Member;
import com.mysite.restsbb.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class NotProd {
    @Autowired
    @Lazy
    private NotProd self;

    private final MemberService memberService;
    private final ArticleService articleService;

    @Bean // PostConstruct와 ApplicationRunner의 사용 목적과 차이를 잘 알아봅시다.
    public ApplicationRunner initNotProd(){
        return args -> {
            self.work1();
        };
    }

    @Transactional
    public void work1(){
        Member member = memberService.join("admin", "1234", "관리자", "admin@test.com").getData();

        Article article = articleService.write(member, "제목1", "내용1").getData();
    }
}
