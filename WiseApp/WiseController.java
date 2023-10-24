package WiseApp;

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
        boolean quit = false;
        String input = "";
        Scanner sc = new Scanner(System.in);
        while (!quit) {
            System.out.print("명령) ");
            input = sc.nextLine();
            // 종료 입력 시 loop break
            if (input.equals("등록")){
                wiseService.postWise();
            }
            if (input.equals("목록")){
                wiseService.getWiseList();
            }
            if (input.equals("종료")){
                quit = true;
                sc.close();
            }
        }
    }
}
