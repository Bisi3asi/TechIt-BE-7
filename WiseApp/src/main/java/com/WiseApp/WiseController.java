package com.WiseApp;

import java.util.Scanner;

/**
 * 사용자 입력에 따른 처리를 실행하는 클래스
 */
public class WiseController {
    WiseService wiseService = new WiseService();

    /**
     * 명언 앱 실행 후 종료 명령 전까지 CRUD 호출(WiseService)
     */
    void launch() {
        if (wiseService.readWise())
            System.out.println("\n프로그램 다시 시작...\n");
        System.out.println(" == 명언 앱 ==");
        boolean quit = false;
        String input = "";
        Scanner sc = new Scanner(System.in);
        while (!quit) {
            // 종료 입력 시 loop break
            System.out.print("명령) ");
            input = sc.nextLine();

            if (input.equals("등록")) {
                String content;
                String author;

                System.out.print("명언 : ");
                content = sc.nextLine();
                System.out.print("작가 : ");
                author = sc.nextLine();

                wiseService.postWise(content, author);
            }
            if (input.equals("삭제")) {
                int id;

                System.out.print("삭제? id = ");
                id = sc.nextInt();
                sc.nextLine(); // 개행 소비
                wiseService.deleteWise(id);
            }
            if (input.equals("수정")) {
                int id;
                String content;
                String author;
                System.out.print("수정? id = ");
                id = sc.nextInt();
                sc.nextLine(); // 개행 소비
                // 기존 명언 출력
                System.out.println("명언(기존) : " + wiseService.getWiseContent(id));
                System.out.print("명언 : ");
                content = sc.nextLine();
                System.out.println("명언(기존) : " + wiseService.getWiseAuthor(id));
                System.out.print("작가 : ");
                author = sc.nextLine();
                wiseService.modifyWise(id, content, author);
            }
            if (input.equals("목록")) {
                wiseService.getWiseList();
            }
            if (input.equals("종료")) {
                wiseService.saveWise();
                quit = true;
                sc.close();
            }
        }
    }
}
