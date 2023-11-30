package com.mysite.restsbb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // 테스트 환경에서만 작동한다.
class RestSbbApplicationTests {

	@Test
	void contextLoads() {
	}

}
