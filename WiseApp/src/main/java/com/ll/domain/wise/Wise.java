package com.ll.domain.wise;

import lombok.Getter;

public class Wise {
    private static int nextId = 1;
    @Getter
    private final int id;
    @Getter
    private final String content;
    @Getter
    private final String author;

    public Wise(int id, String content, String author){
        this.content = content;
        this.author = author;
        this.id = id;
        // 파일 영속성(불러오기) 이후 등록 시
        if (nextId <= id) nextId = id + 1;
    }

    public Wise(String content, String author){
        this.content = content;
        this.author = author;
        this.id = nextId;
        nextId++;
    }
}
