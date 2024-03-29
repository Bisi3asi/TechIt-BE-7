package com.example.sbrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class SbrestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbrestApplication.class, args);
	}

}
