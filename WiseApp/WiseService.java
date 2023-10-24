package WiseApp;

import java.util.Scanner;

/**
 * Wise에 대한 CRUD 기능을 수행하는 클래스
 */
public class WiseService {

    public void postWise(){
        Scanner sc = new Scanner(System.in);
        String content; String author;

        System.out.print("명언 : ");
        content = sc.nextLine();
        System.out.print("작가 : ");
        author = sc.nextLine();
    }
}
