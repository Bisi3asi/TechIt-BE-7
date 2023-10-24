package WiseApp;

import java.util.ArrayList;

/**
 * 명언 객체를 저장하는 클래스
 */
public class WiseRepository {
    static ArrayList<Wise> wiseList = new ArrayList<>(); // 명언 객체를 저장하는 리스트

    /**
     * 리스트에 생성된 명언 객체를 저장하고, 증가되는 명언번호를 출력하는 메소드
     * @param wise (명언내용, 작가, ID(증가))
     */
    public void add(Wise wise){
        wiseList.add(wise);
        System.out.println(wise.getId()+"번 명언이 등록되었습니다.");
    }

//    public Wise findByID(int id, ArrayList<Wise> wiseList){
//        for (Wise w : wiseList) {
//            if (w.getId() == id){
//                return w;
//            }
//        }
//        System.out.println(id+"번 명언은 존재하지 않습니다.");
//        return null;
//    }

    public ArrayList<Wise> findAll(){
        return wiseList;
    }

}
