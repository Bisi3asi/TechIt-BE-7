package WiseApp;

/**
 * 명언 객체를 생성하는 클래스
 */
public class Wise {
    private final String content; // 명언 내용
    private final String author; // 명언 작가
    private final int id; // 생성 id
    private static int nextId = 1; // 다음 생성될 객체의 id를 지정하는 static 변수

    Wise(String content, String author){
        this.content = content;
        this.author = author;
        id = nextId;
        nextId++;
    }

    int getId(){
        return id;
    }

    String getContent(){
        return content;
    }

    String getAuthor(){
        return author;
    }
}
