package com.ll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class CalcTest {
    @Test
    @DisplayName("Calc에 run 메서드는 int를 리턴한다.")
    void t1() throws IOException {
        int rs = Calc.run("");
    }

    @Test
    @DisplayName("기초 더하기.")
    void t2() throws IOException {
        // assert : 단언하다
        int rs = Calc.run("10 + 10");
        assertThat(rs).isEqualTo(20);
    }
    @Test
    @DisplayName("기초 빼기.")
    void t3() throws IOException {
        int rs = Calc.run("10 - 10");
        assertThat(rs).isEqualTo(0);
    }

    @Test
    @DisplayName("기초 곱하기.")
    void t4() throws IOException {
        int rs = Calc.run("10 * 10");
        assertThat(rs).isEqualTo(100);
    }
    @Test
    @DisplayName("기초 더하기.")
    void t5() throws IOException {
        int rs = Calc.run("10 / 10");
        assertThat(rs).isEqualTo(1);
    }

    @Test
    @DisplayName("괄호가 있는 기초 더하기.")
    void t6() throws IOException {
        int rs = Calc.run("(10 + 10) - (20 + 30)");
        assertThat(rs).isEqualTo(-30);
    }

    @Test
    @DisplayName("괄호가 있는 기초 더하기, 곱하기.")
    void t7() throws IOException {
        int rs = Calc.run("(10 + 20) * (2 + 3)");
        assertThat(rs).isEqualTo(150);
    }

    @Test
    @DisplayName("괄호를 포함한 사칙연산")
    void t8() throws IOException {
        int rs = Calc.run("(1 + 2 * 3) - (10 / 2)");
        assertThat(rs).isEqualTo(2);
    }

    @Test
    @DisplayName("이중 괄호")
    void t9() throws IOException {
        int rs = Calc.run("((1 + 2) - (3 * 4))");
        assertThat(rs).isEqualTo(-9);
    }

    @Test
    @DisplayName("중첩된 사칙연산")
    void t10() throws IOException {
        int rs = Calc.run("(3 * 1 + (1 - (4 * 1 - (1 - 1))))");
        assertThat(rs).isEqualTo(0);
    }

    @Test
    @DisplayName("괄호 괄호의 중첩")
    void t11() throws IOException {
        int rs = Calc.run("((3 + 6) + 9) * 2 - 2 * ((3 + 4 / 2) + (1 + 3) * 2)");
        assertThat(rs).isEqualTo(10);
    }
}
