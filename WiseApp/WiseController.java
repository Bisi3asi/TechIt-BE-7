package WiseApp;

import java.util.Scanner;

/**
 * 사용자 입력에 따른 처리를 실행하는 클래스
 */
public class WiseController {
    void launch() {
        boolean quit = false;
        String input = "";
        Scanner sc = new Scanner(System.in);
        while (!quit) {
            System.out.print("명령) ");
            input = sc.next();
            // 종료 입력 시 loop break
            if (input.equals("종료")){
                quit = true;
            }
        }
    }
}
