package com.ll.domain.wise.controller;

import com.ll.domain.wise.WiseJDBC;
import com.ll.domain.wise.WiseService;
import java.util.ArrayList;
import java.util.Scanner;

public class WiseController {
    WiseJDBC wiseJDBC = new WiseJDBC();
    WiseService wiseService = new WiseService();
    private final Scanner sc;

    public WiseController(Scanner sc) {
        this.sc = sc;
    }

    public void start() throws Exception {
        boolean isQuit = false;
        System.out.println("== 명언 앱 == ");
        readWise();

        while (!isQuit) {
            String input = returnInput();
            if (input.equals("등록")) {
                postWise();
            }
            if (input.equals("목록")) {
                showWiseList();
            }
            if (input.matches("삭제\\?id=\\d+")) {
                int id = Integer.parseInt(input.substring(6));
                deleteWise(id);
            }
            if (input.matches("수정\\?id=\\d+")) {
                int id = Integer.parseInt(input.substring(6));
                modifyWise(id);
            }
            if (input.equals("빌드")) {
                buildWise();
            }
            if (input.equals("종료")) {
                isQuit = true;
                saveWise();
            }
        }
    }

    public String returnInput() {
        System.out.print("명령) ");
        return sc.nextLine();
    }

    public void postWise() {
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        int id = wiseService.postWise(content, author);
        System.out.println(id + "번 명언이 등록되었습니다.");
    }

    public void showWiseList() {
        System.out.println("번호 / 작가 / 명언\n----------------------");
        ArrayList<String> totalWiseList = wiseService.getWiseList();
        totalWiseList.forEach(System.out::println);
    }

    public void deleteWise(int id) {
        boolean isvalid = wiseService.deleteWise(id);

        if (!isvalid) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
        }
        if (isvalid) {
            System.out.println(id + "번 명언이 삭제되었습니다.");
        }
    }

    public void modifyWise(int id) {
        // findResult = {"기존 명언", "기존 작가"}
        String[] findResult = wiseService.findWise(id);

        if (findResult == null) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
        }
        if (findResult != null) {
            System.out.print("명언(기존) : " + findResult[0] + "\n명언 : ");
            String content = sc.nextLine();
            System.out.println("작가(기존) : " + findResult[1] + "\n작가 : ");
            String author = sc.nextLine();
            wiseService.modifyWise(id, content, author);
        }
    }

    public void saveWise() {
        if (!wiseService.saveWise()) {
            System.out.println("[ERROR] : SAVE FAILED");
        }
    }

    public void readWise() {
        wiseService.readWise();
    }

    public void buildWise() {
        String savePath = wiseService.buildWise();
        if (savePath.equals("ERROR")) {
            System.out.println("[ERROR] : SAVE FAILED");
        }
        if (!savePath.equals("ERROR")) {
            System.out.println(savePath + " 파일의 내용이 갱신되었습니다.");
        }
    }
}
