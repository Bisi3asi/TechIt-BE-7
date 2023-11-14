package com.example.springbootdemo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        return "계산결과 : %d".formatted(a + b);
    }

    @GetMapping("/calc4")
    @ResponseBody
    String showCalc4(
            @RequestParam(defaultValue = "0") double a,
            @RequestParam(defaultValue = "0") double b) {
        return "계산결과 : %f".formatted(a + b);
    }

    @GetMapping("/calc5")
    @ResponseBody
    String showCalc5(
            @RequestParam(defaultValue = "0") String a,
            @RequestParam(defaultValue = "0") String b) {
        return "계산결과 : %s".formatted(a + b);
    }

    // 브라우저로 보내는 Response 값이 int 인 경우 :
    // 함수의 리턴값을 스프링이 문자열로 캐스팅해 브라우저로 전송한다
    @GetMapping("/calc6")
    @ResponseBody
    int showCalc6(@RequestParam(defaultValue = "0") int a, @RequestParam(defaultValue = "0") int b) {
        return a + b;
    }

    // java에서의 리턴값은 java에서만 유효하며, 클라이언트로 전송되는 리턴값은 모두 String화
    @GetMapping("/calc7")
    @ResponseBody
    boolean showCalc7(@RequestParam(defaultValue = "0") int a, @RequestParam(defaultValue = "0") int b) {
        return a > b;
    }

    // 객체를 리턴할 시는 객체를 String화 해서 json 방식으로 리턴한다
    @GetMapping("/calc8")
    @ResponseBody
    Person showCalc8(String name, int age) {
        // private 필드에 Getter를 쓰면 가져온다.
        return new Person(name, age);
    }

    // keyMap value = object형으로 uri에서 받은 값을 클라이언트에게 전달하기
    @GetMapping("/calc9")
    @ResponseBody
    Map<String, Object> showCalc9(String name, int age) {
        return Map.of(
                "name", name,
                "age", age
        );
    }

    // return List
    @GetMapping("/calc10")
    @ResponseBody
    List<Person> showCalc10(String name, int age) {
        return new ArrayList<>() {{
            add(new Person(name, age));
            add(new Person("Tom", 42));
            add(new Person("Ginger", 12));
        }};
    }

    // Arrays
    @GetMapping("/calc11")
    @ResponseBody
    int[] showCalc11() {
        return new int[]{10, 50, 40};
    }

    // html 형식으로 입력 시 해당 내용을 완성해서 출력한다
    // ctrl + u로 소스 확인 가능
    @GetMapping("/calc12")
    @ResponseBody
    String showCalc12() {
        return """
                <div>
                <input type="text" name="username" placeholder="이름을 입력하세요.">
                </div>                
                """;
    }

    @GetMapping("/calc13")
    @ResponseBody
    String showCalc13(String content) {
        StringBuilder sb = new StringBuilder();

        sb.append("<div>");
        sb.append("<input type=\"text\" name=\"username\" placeholder=\"이름을 입력하세요.\" value =\"%s\"");
        sb.append("</div>");
        return sb.toString().formatted(content);
    }

    // responsebody가 없으면 templates에 있는 해당 리턴값 명의 html을 호출한다.
    @GetMapping("/calc14")
    String showCalc14() {
        return "home";
    }

    // 뷰에 담을 데이터는 model 객체를 통해 전송한다.
    @GetMapping("/calc15")
    String showCalc15(Model model) {
        // addAttribute를 통해 attributeName과 value를 매핑해 view로 전송한다.
        model.addAttribute("v1", "Hello");
        model.addAttribute("v2", "World");
        return "home";
    }
}

@AllArgsConstructor
class Person {
    @Getter
    private String name;
    @Getter
    private int age;
}
