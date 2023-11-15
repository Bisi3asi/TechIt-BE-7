package com.ll.domain.wise.repository;

import com.ll.domain.wise.Wise;
import com.ll.domain.wise.WiseJDBC;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class WiseMemoryRepository implements WiseRepository {
    @Getter
    static List<Wise> wiseList = new ArrayList<>();
    // 파일 영속성, 빌드 경로

    public int postWise(String content, String author) {
        wiseList.add(new Wise(content, author));
        // 생성된 명언의 id값 리턴
        return wiseList.get(wiseList.size() - 1).getId();
    }

    /**
     * @param id 찾고자 하는 명언의 id값
     * @return 해당 id가 없을 시 -1 리턴 / 있을 시 해당 id 명언이 저장된 리스트 인덱스 리턴
     */
    public int getWiseIndexById(int id) {
        for (Wise wise : wiseList) {
            if (wise.getId() == id) {
                return wiseList.indexOf(wise);
            }
        }
        return -1;
    }

    public boolean deleteWise(int id) {
        int deleteIndex = getWiseIndexById(id);
        if (deleteIndex == -1) {
            return false;
        }
        wiseList.remove(deleteIndex);
        return true;
    }

    public void modifyWise(int id, String content, String author){
        wiseList.set(getWiseIndexById(id), new Wise(id, content, author));
    }

    public boolean saveWise(){
        return WiseJDBC.saveWise(wiseList);
    }

    public boolean readWise(){
        return WiseJDBC.readWise(wiseList);
    }
}
