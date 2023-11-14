package com.example.springbootdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody // / 요청에 대한 응답 : 이 함수의 리턴값을 그대로 브라우저에 전송하라는 의미
    String showMain(){
        return "안녕하세요.";
    }

    @GetMapping("/about")
    @ResponseBody // /about 요청에 대한 응답 : 이 함수의 리턴값을 그대로 브라우저에 전송하라는 의미
    String showAbout(){
        return "개발자 커뮤니티";
    }
}
