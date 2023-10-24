package WiseApp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Wise에 대한 CRUD 기능을 수행하는 클래스
 */
public class WiseService {
    WiseRepository wiseRepository = new WiseRepository();

    public void postWise() {
        Scanner sc = new Scanner(System.in);
        String content;
        String author;

        System.out.print("명언 : ");
        content = sc.nextLine();
        System.out.print("작가 : ");
        author = sc.nextLine();

        wiseRepository.add(new Wise(content, author));
    }

    /**
     * 입력값 "목록"에 따른 모든 명언 리스트 출력
     * @todo 리팩토링 무조건 한다
     */
    public void getWiseList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (int i = wiseRepository.findAll().size() - 1; i >= 0; i--) {
            System.out.println(wiseRepository.findAll().get(i).getId() +
                    " / " + wiseRepository.findAll().get(i).getAuthor() +
                    " / " + wiseRepository.findAll().get(i).getContent());
        }
    }
}
