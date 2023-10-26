package com.WiseApp;

import java.util.Scanner;

/**
 * 사용자 입력에 따른 처리를 실행하는 클래스
 */
public class WiseController {
    WiseService wiseService = new WiseService();

    /**
     * 명언 앱 실행 후 종료 명령 전까지 CRUD 호출(WiseService)
     * @todo 메소드별로 다시 split
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

            // 웹 방식의 검색을 고려한 문자열 파싱 방법(모든 예제의 값이 유효하게 작동)
            // ex) 삭제?author=A&id=2
            // ex) 삭제?id=2&author=B
            // ex) 삭제?archive=true&id=2&author=C\
            if (input.startsWith("삭제?")) {
                String[] inputBits = input.split("\\?", 2);
                String action = inputBits[0]; // 삭제?
                String queryString = inputBits[1];

                String[] queryStringBits = queryString.split("&");
                for (int i = 0; i < queryStringBits.length; i++){
                    String queryParamStr = queryStringBits[i];
                    String[] queryParamStrBits = queryParamStr.split("=", 2);

                    String paramName = queryParamStrBits[0];
                    String paramValue = queryParamStrBits[1];
                    if(paramName.equals("id")) {
                        wiseService.deleteWise(Integer.valueOf(paramValue));
                    }
                }
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

            if (input.equals("빌드")) {
                if (wiseService.saveWise())
                    System.out.println(wiseService.PATH + " 파일의 내용이 갱신되었습니다.");
            }

            if (input.equals("종료")) {
                wiseService.saveWise();
                quit = true;
                sc.close();
            }
        }
    }
}
