package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

// 입력 확인
public class TestUtilTest {
    @Test
    @DisplayName("TestUtil.genScanner() 테스트")
    void t1() {
        Scanner scanner = TestUtil.genScanner(
                """
                등록
                나의 죽음을 적들에게 알리지 말라!
                이순신
                """.stripIndent());
        String cmd = scanner.nextLine();
        String content = scanner.nextLine();
        String authorName = scanner.nextLine();

        assertThat(cmd).isEqualTo("등록");
        assertThat(content).isEqualTo("나의 죽음을 적들에게 알리지 말라!");
        assertThat(authorName).isEqualTo("이순신");
    }

    @Test
    @DisplayName("TestUtil.setOutToByteArray() 테스트")
    void t2() {
        ByteArrayOutputStream byteArrayOutputStream = TestUtil.setOutToByteArray();

        System.out.println("2 / 이순신 / 나의 죽음을 적들에게 알리지 말라!");

        String out = byteArrayOutputStream.toString().trim();

        assertThat(out).isEqualTo("2 / 이순신 / 나의 죽음을 적들에게 알리지 말라!");

        TestUtil.clearSetOutToByteArray(byteArrayOutputStream);
        System.out.println("이제는 화면에 출력됩니다.");
    }
}