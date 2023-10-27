package com.ll.domain;

import com.ll.standard.util.*;
import java.util.HashMap;

public class Rq {
    public String cmd;
    public String action;
    public String queryString;
    public HashMap<String, String> paramMap = new HashMap<>();

    Rq (String cmd) {
        this.cmd = cmd;

        String[] cmdBits = cmd.split("\\?", 2);
        if (cmdBits.length == 2) {
            this.action = cmdBits[0].trim();
            this.queryString = cmdBits[1].trim();
        }
        if (cmdBits.length == 1) {
            this.action = cmdBits[0].trim();
            this.queryString ="";
        }
        String[] queryStringBits = queryString.split("&");

        for (int i = 0; i < queryStringBits.length; i++) {
            String queryParamStr = queryStringBits[i];
            String[] queryParamStrBits = queryParamStr.split("=", 2);

            String paramName = queryParamStrBits[0];
            String paramValue = queryParamStrBits[1];
            if (paramName != null && paramValue != null ) {
                paramMap.put(paramName, paramValue);
            }
        }
    }

    String getAction() {
        return action;
    }
    public int getParamValueAsInt(String paramName, int defaultValue) {
        return Ut.str.parseInt(paramMap.get(paramName), defaultValue);
    }
}
