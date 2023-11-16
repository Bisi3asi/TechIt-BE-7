package com.example.springbootdemo.domain.article.article.controller;

import com.example.springbootdemo.domain.article.article.entity.Article;
import com.example.springbootdemo.domain.article.article.service.ArticleService;
import com.example.springbootdemo.global.rq.Rq;
import com.example.springbootdemo.global.rsData.RsData;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor // 생성자 주입
public class ArticleController {
    private final ArticleService articleService;
    private final Rq rq;

    @GetMapping("/write")
    String showWrite() {
        return "write";
    }

    @Data
    public static class WriteForm {
        @NotBlank
        private String title;
        @NotBlank
        private String body;
    }

    @PostMapping("/write")
    @ResponseBody
    RsData<Article> write(@Valid WriteForm writeForm) {
        // validation을 통한 입력값 검증
        Article article = articleService.write(writeForm.title, writeForm.body);
        RsData<Article> rs = new RsData(
                "S-1",
                "%d번 게시물이 작성되었습니다.".formatted(article.getId()),
                article
        );
        return rs;
    }

    @PostMapping("/write2")
    @ResponseBody
    void write2(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String title = req.getParameter("title");
        String body = req.getParameter("body");
        Article article = articleService.write(title, body);

        RsData<Article> rs = new RsData(
                "S-1",
                "%d번 게시물이 작성되었습니다.".formatted(article.getId()),
                article
        );

        ObjectMapper objectMapper = new ObjectMapper();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println(objectMapper.writeValueAsString(rs));
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

    @GetMapping("/lifeCycle")
    @ResponseBody
    String lifeCyclepointer(){
        return articleService.toString();
    }

    @GetMapping("/httpServletRequestPointer")
    @ResponseBody
    String articleServiceRequestPointer(HttpServletRequest req){
        return req.toString();
    }

    @GetMapping("/httpServletResponsePointer")
    @ResponseBody
    String articleServiceResponsePointer(HttpServletResponse resp){
        return resp.toString();
    }

    // requestScope를 붙이면 요청 주기에 한해서만 공유 객체가 살아있다.
    @GetMapping("/rqPointer")
    @ResponseBody
    String rqPointer(){
        return rq.toString();
    }

    @GetMapping("/rqTest")
    String showRqTest(Model model) {
        String rqToString = rq.toString();
        model.addAttribute("rqToString", rqToString);
        return "rqTest";
    }
}

