package com.ll.domain;

import java.util.Scanner;

/**
 * 사용자 입력에 따른 처리를 실행하는 클래스
 */
public class WiseController {
    public final WiseService wiseService = new WiseService();
    private final Scanner sc = new Scanner(System.in);

    /**
     * 명언 앱 실행 후 종료 명령 전까지 CRUD 호출(WiseService)
     */
    public void launch() {
        if (wiseService.readWise())
            System.out.println("\n프로그램 다시 시작...\n");
        System.out.println(" == 명언 앱 ==");

        boolean quit = false;
        while (!quit) {
            String cmd = "";
            System.out.print("명령) ");
            cmd = sc.nextLine();

            if (cmd.equals("등록")) {
                String content;
                String author;

                System.out.print("명언 : ");
                content = sc.nextLine();
                System.out.print("작가 : ");
                author = sc.nextLine();
                wiseService.postWise(content, author);
            }

            // 삭제(웹 방식의 입력을 고려한 문자열 파싱 방법 적용)
            // (이하 예제의 값이 유효하게 작동)
            // ex) 삭제?author=A&id=2
            // ex) 삭제?id=2&author=B
            // ex) 삭제?archive=true&id=2&author=C

            if (cmd.startsWith("삭제?")) {
                Rq rq = new Rq(cmd);
                int id = _getParamValueAsInt(rq, "id", -1);
                wiseService.deleteWise(id);
            }
            // 수정(웹 방식의 입력을 고려한 문자열 파싱 방법 적용)
            if (cmd.startsWith("수정?")) {
                Rq rq = new Rq(cmd);
                int id = _getParamValueAsInt(rq, "id", -1);
                if (wiseService.findById(id)) {
                    System.out.println("명언(기존) : " + wiseService.getWiseContent(id));
                    System.out.print("명언 : ");
                    String content = sc.nextLine();
                    System.out.println("명언(기존) : " + wiseService.getWiseAuthor(id));
                    System.out.print("작가 : ");
                    String author = sc.nextLine();
                    wiseService.modifyWise(id, content, author);
                }
            }

            if (cmd.equals("목록")) {
                wiseService.printWiseList();
            }

            if (cmd.equals("빌드")) {
                wiseService.saveWise();
            }

            if (cmd.equals("종료")) {
                wiseService.saveWise();
                quit = true;
                sc.close();
            }
        }
    }

    public int _getParamValueAsInt(Rq rq, String paramName, int defaultValue) {
        return rq.getParamValueAsInt(paramName, defaultValue);
    }
}

