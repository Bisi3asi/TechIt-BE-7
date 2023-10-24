package WiseApp;

/**
 * 명언 객체를 생성하는 클래스
 */
public class Wise {
    private final String content; // 명언 내용
    private final String author; // 명언 작가
    private static int id = 0; // 고유 명언번호 생성당 1씩 증가 위해 static 선언

    Wise(String content, String author){
        this.content = content;
        this.author = author;
        id++;
    }

    static int getId(){
        return Wise.id;
    }
}
