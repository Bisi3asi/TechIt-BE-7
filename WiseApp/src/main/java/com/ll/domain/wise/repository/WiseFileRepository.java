package com.ll.domain.wise.repository;

import static com.ll.domain.wise.repository.WiseMemoryRepository.wiseList;

import com.ll.domain.wise.Wise;
import java.io.FileReader;
import java.io.FileWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class WiseFileRepository implements WiseRepository {
    private final String PATH = ("data.json");
    private final String ERROR = ("ERROR");

    /**
     * @return 저장 성공 시 PATH / 실패 시 -1 리턴
     */
    public String WriteWise() {
        try {
            JSONArray wiseInfoArray = new JSONArray();
            for (Wise wise : wiseList) {
                JSONObject wiseInfo = new JSONObject();
                wiseInfo.put("id", String.valueOf(wise.getId()));
                wiseInfo.put("content", wise.getContent());
                wiseInfo.put("author", wise.getAuthor());
                wiseInfoArray.add(wiseInfo);
            }
            FileWriter fw = new FileWriter(PATH);
            fw.write(wiseInfoArray.toJSONString());
            fw.flush();
            fw.close();
            return PATH;
        } catch (Exception e){
            return ERROR;
        }
    }


    /**
     * @return 불러오기 성공 시 true / 실패 시 false 리턴
     */
    public boolean readWise() {
        try {
            JSONParser jsonParser = new JSONParser();
            Object object = jsonParser.parse(new FileReader(PATH));
            JSONArray jsonArray = (JSONArray) object;

            if (!jsonArray.isEmpty()) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    int id = Integer.valueOf((String) jsonObject.get("id"));
                    String author = (String) jsonObject.get("author");
                    String content = (String) jsonObject.get("content");
                    wiseList.add(new Wise(id, content, author));
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
