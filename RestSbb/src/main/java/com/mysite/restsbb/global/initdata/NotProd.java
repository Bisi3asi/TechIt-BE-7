package com.mysite.restsbb.global.initdata;

import com.mysite.restsbb.article.service.ArticleService;
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
        if (memberService.count() > 0) return; // 데이터가 하나라도 적재되어 있으면 create 안함

        Member member1 = memberService.join("admin", "1234", "admin@test.com", "admin").getData();
        Member member2 = memberService.join("user1", "1234", "user1@test.com", "user1").getData();
        Member member3 = memberService.join("user2", "1234", "user1@test.com", "user2").getData();

        for (int i = 0; i < 10; i++){
            Article article = articleService.write(member2, "제목"+i, "내용"+i).getData();
        }

    }
}
