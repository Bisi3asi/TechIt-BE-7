package com.example.sbrest.global.springdoc;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "RESTSB_API", version = "v1"))
public class SpringDocConfig {

	@Bean
	public GroupedOpenApi apiGroupV1(){
		return GroupedOpenApi.builder()
			.group("APIv1")
			.pathsToMatch("/api/v1/**")
			.build();
	}
}
