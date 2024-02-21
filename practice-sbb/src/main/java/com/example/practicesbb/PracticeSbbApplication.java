package com.example.practicesbb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PracticeSbbApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeSbbApplication.class, args);
	}

}
