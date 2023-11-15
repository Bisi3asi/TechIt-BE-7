package com.ll.domain.wise;

import com.ll.domain.wise.repository.WiseFileRepository;
import com.ll.domain.wise.repository.WiseMemoryRepository;
import java.util.ArrayList;
import java.util.List;


public class WiseService {
    WiseMemoryRepository wiseMemoryRepository = new WiseMemoryRepository();
    WiseFileRepository wiseFileRepository = new WiseFileRepository();

    /**
     * 생성할 명언 전달 to repository
     *
     * @return 생성한 명언 id 리턴 to controller
     */
    public int postWise(String content, String author) {
        return wiseMemoryRepository.postWise(content, author);
    }

    /**
     * @return totalList : 양식 적용 명언 목록 문자열
     */
    public ArrayList<String> getWiseList() {
        List<Wise> wiseList = WiseMemoryRepository.getWiseList();
        ArrayList<String> totalWiseList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (int index = wiseList.size() - 1; index >= 0; index--) {
            sb.append(wiseList.get(index).getId()).append(" / ")
                    .append(wiseList.get(index).getAuthor()).append(" / ")
                    .append(wiseList.get(index).getContent());
            totalWiseList.add(sb.toString());
            sb.setLength(0);
        }
        return totalWiseList;
    }

    /**
     * @return 삭제 성공 시 true / 실패 시 false 리턴
     */
    public boolean deleteWise(int id) {
        return wiseMemoryRepository.deleteWise(id);
    }

    public String[] findWise(int id) {
        int findIndex = wiseMemoryRepository.getWiseIndexById(id);
        if (findIndex == -1) {
            return null;
        }
        return new String[]{
                WiseMemoryRepository.getWiseList().get(findIndex).getContent(),
                WiseMemoryRepository.getWiseList().get(findIndex).getAuthor()
        };
    }

    public void modifyWise(int id, String content, String author) {
        wiseMemoryRepository.modifyWise(id, content, author);
    }

    public boolean saveWise(){
        return wiseMemoryRepository.saveWise();
    }

    public String buildWise(){
        return wiseFileRepository.WriteWise();
    }

    public boolean readWise(){
        return wiseMemoryRepository.readWise();
    }
}
