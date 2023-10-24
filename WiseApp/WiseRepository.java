package WiseApp;

import java.util.ArrayList;

/**
 * 명언 객체를 저장하는 클래스
 */
public class WiseRepository {
    ArrayList<Wise> wiseList = new ArrayList<>(); // 명언 객체를 저장하는 리스트

    /**
     * 리스트에 생성된 명언 객체를 저장하고, 증가되는 명언번호를 출력하는 메소드
     *
     * @param wise  (명언내용, 작가, ID(증가))
     */
    public void add(Wise wise) {
        wiseList.add(wise);
        System.out.println(wise.getId() + "번 명언이 등록되었습니다.");
    }

    /**
     * @param id       검색할 id
     * @param wiseList 명언 저장된 리스트
     * @return 검색한 id의 wiseList 상 index 리턴
     */
    public int findByID(int id) {
        for (Wise w : wiseList) {
            if (w.getId() == id) {
                return wiseList.indexOf(w);
            }
        }
        return -1;
    }

    public ArrayList<Wise> findAll() {
        return wiseList;
    }


}
