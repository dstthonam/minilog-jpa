package com.onboarding.projects.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	    @Bean
	    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		        http
		        	// CSRF 방지 기능 비활성화
		            .csrf(csrf -> csrf.disable())
		            .authorizeHttpRequests(auth -> auth
		                .requestMatchers(
		                    "/swagger-ui/**",
		                    "/v3/api-docs/**",
		                    "/api/v2/auth/login"
		                ).permitAll()
		                .requestMatchers(HttpMethod.POST, "/api/v2/user").permitAll()
		                .requestMatchers(HttpMethod.DELETE, "/api/v2/user/{userId}").hasRole("ADMIN")
		                // 매 요청마다 인증
		                //.anyRequest().permitAll()
		                .anyRequest().authenticated()
		            );
		
		        return http.build();
		}
		
}
