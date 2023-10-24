package WiseApp;

import java.util.ArrayList;

/**
 * Wise에 대한 CRUD 기능을 수행하는 클래스
 */
public class WiseService {
    WiseRepository wiseRepository = new WiseRepository();

    public void postWise(String content, String author) {
        wiseRepository.add(new Wise(content, author));
    }

    public String getWiseAuthor(int id) {
        return wiseRepository.wiseList.get(wiseRepository.findByID(id)).getAuthor();
    }

    public String getWiseContent(int id) {
        return wiseRepository.wiseList.get(wiseRepository.findByID(id)).getContent();
    }

    /**
     * 입력값 "목록"에 따른 모든 명언 리스트 출력
     *
     * @todo 리팩토링
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

    /**
     * 입력된 id 값에 따른 명언 삭제
     *
     * @param id 명언 번호
     */
    public void deleteWise(int id) {
        // searchResult : 검색결과 ( -1 : 검색결과 없음, 그 외 : 검색된 index)
        int searchResult = wiseRepository.findByID(id);
        if (searchResult == -1) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
        }
        if (searchResult != -1) {
            wiseRepository.wiseList.remove(searchResult);
            System.out.println(id + "번 명언이 삭제되었습니다.");
        }
    }


    /**
     * 입력한 id 값에 따른 명언 수정
     *
     * @param id      수정 대상 id
     * @param content 수정할 내용
     * @param author  수정할 작가
     */
    public void modifyWise(int id, String content, String author) {
        // searchResult : 검색결과 ( -1 : 검색결과 없음, 그 외 : 검색된 index)
        int searchResult = wiseRepository.findByID(id);
        if (searchResult == -1) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
        }
        // Post로 수정값 갱신 후 기존 값 삭제
        if (searchResult != -1) {
            wiseRepository.wiseList.add(searchResult, new Wise(content, author, searchResult + 1));
            wiseRepository.wiseList.remove(searchResult + 1);
        }
    }
}
