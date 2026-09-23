package com.onboarding.projects.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiDocumentationConfig {
		
		@Bean
		public OpenAPI apiCocumentation() {
				return new OpenAPI()
						.info(
								new Info()
											.title("TODO List API")
											.version("1.0")
											.description("Spring Boot3을 이용한 TODO List API 문서"));
		}
}
