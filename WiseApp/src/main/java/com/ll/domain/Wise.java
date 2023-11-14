package com.ll.domain;

import lombok.Getter;

/**
 * 명언 객체를 생성하는 클래스
 * Getter, Setter에 대한 이해
 *
 */
public class Wise {
    @Getter
    private final String content; // 명언 내용
    @Getter
    private final String author; // 명언 작가
    @Getter
    private final int id; // 생성 id
    @Getter
    private static int nextId = 1; // 다음 생성될 객체의 id를 지정하는 static 변수

    /**
     * 신규 생성자(등록)
     *
     * @param content the content
     * @param author  the author
     */
    Wise(String content, String author) {
        this.content = content;
        this.author = author;
        id = nextId;
        nextId++;
    }

    /**
     * 수정 생성자(수정)
     *
     * @param content the content
     * @param author  the author
     * @param id      the id
     */
    Wise(String content, String author, int id) {
        this.content = content;
        this.author = author;
        this.id = id;
        if (nextId <= id) nextId = id + 1;
    }
}