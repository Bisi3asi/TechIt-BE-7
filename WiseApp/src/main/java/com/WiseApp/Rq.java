package com.WiseApp;

import java.util.HashMap;

public class Rq {
    String cmd;
    String action;
    String queryString;
    HashMap<String, String> paramMap;

    Rq(String cmd) {
        this.cmd = cmd;
        String[] cmdBits = cmd.split("\\?, 2");
        this.action = cmdBits[0].trim();
        this.queryString = cmdBits[1].trim();

        String[] queryStringBits = queryString.split("&");
        for (int i = 0; i < queryStringBits.length; i++) {
            String queryParamStr = queryStringBits[i];
            String[] queryParamStrBits = queryParamStr.split("=", 2);

            String paramName = queryParamStrBits[0];
            String paramValue = queryParamStrBits[1];
            paramMap.put(paramName, paramValue);
        }
    }

    String getAction(){
        return action;
    }

    public int getParamAsInt(String paramName, int defaultValue){
        String paramValue = paramMap.get(paramName);
        try {
            return Integer.parseInt(paramValue);
        } catch (NumberFormatException e) {
            return defaultValue;
        }

    }
}
