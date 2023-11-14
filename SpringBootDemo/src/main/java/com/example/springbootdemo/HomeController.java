package com.example.springbootdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
        // / 요청에 대한 응답 : 이 함수의 리턴값을 그대로 브라우저에 전송하라는 의미
    String showMain() {
        return "안녕하세요.";
    }

    @GetMapping("/about")
    @ResponseBody
        // /about 요청에 대한 응답 : 이 함수의 리턴값을 그대로 브라우저에 전송하라는 의미
    String showAbout() {
        return "개발자 커뮤니티";
    }

    @GetMapping("/calc")
    @ResponseBody
        // 매개 값을 주지 않으면 Spring에서는 자동으로 인자에 null 값을 넣으려고 시도한다.
    String showCalc(int a, int b) { // null 값을 집어 넣을 수 없는 primitive type이므로 error 500 반환
        return "계산기";
    }

    @GetMapping("/calc2")
    @ResponseBody
    String showCalc2(Integer a, Integer b) { // Integer에는 null 값이 지원된다.
        // URI로 calc2/?a=100%b=200 주면 값 출력
        // 안주면 null 출력
        return "a : " + a + " b : " + b;
    }

        // URI로 받는 Request 값은 모두 String 형이다.
        // 따라서 Request 변수 설정 시 String으로 받아야 다양한 데이터 받을 수 있다.
    @GetMapping("/calc3")
    @ResponseBody
    String showCalc3(
            @RequestParam(defaultValue = "0") int a,
            @RequestParam(defaultValue = "0") int b) {
        return "계산결과 : %d".formatted(a+b);
    }

    @GetMapping("/calc4")
    @ResponseBody
    String showCalc4(
            @RequestParam(defaultValue = "0") double a,
            @RequestParam(defaultValue = "0") double b) {
        return "계산결과 : %f".formatted(a+b);
    }

    @GetMapping("/calc5")
    @ResponseBody
    String showCalc5(
            @RequestParam(defaultValue = "0") String a,
            @RequestParam(defaultValue = "0") String b) {
        return "계산결과 : %s".formatted(a+b);
    }
}
