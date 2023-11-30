package com.mysite.restsbb.global.rsdata;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RsData<T> {
    private final String resultCode;
    private final String msg;
    private final T data;
    private final int statusCode;

    public static <T> RsData<T> of(String resultCode, String msg, T data) {
        int statusCode = Integer.parseInt(resultCode);
        return new RsData<>(resultCode, msg, data, statusCode);
    }

    public boolean isSuccess(){
        return statusCode >= 200 && statusCode < 400;
        // 100 : data 수신
        // 200 : 성공
        // 300 : 리다이렉션 메시지
        // 400 : 서버 에러
        // 500 : 클라이언트 에러
    }

    public boolean isFail(){
        return !isSuccess();
    }
}
