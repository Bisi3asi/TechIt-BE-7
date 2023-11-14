package com.example.springbootdemo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/article")
public class ArticleController {
    private Article lastArticle;

    @GetMapping("/write")
    String showWrite(){
        return "write";
    }

    @GetMapping("/doWrite")
    @ResponseBody
    Map<String, Object> doWrite(String title, String body){

        Map<String, Object> rs = new HashMap<>();
        lastArticle = new Article(1, title, body);
        rs.put("msg", "1번 게시물이 작성되었습니다.");
        rs.put("data", lastArticle);
        return rs;
    }

    @GetMapping("/getLastArticle")
    @ResponseBody
    Article getLastArticle(){
        return lastArticle;
    }
}

@Getter
@AllArgsConstructor
class Article{
    private long id;
    private String title;
    private String body;
}
