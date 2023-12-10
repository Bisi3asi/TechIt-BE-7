package com.mysite.restsbb.global.util.jwt;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class JwtUtilTest {
    @Test
    @DisplayName("t1")
    void t1(){
        Map<String, Object> data = Map.of("name", "홍길동", "age", "22");
        String jwtToken = JwtUtil.encode(60, data);

        System.out.println(jwtToken);

        assertThat(jwtToken).isNotNull();
    }
}
