package com.studentslist.servicenowassessmentserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ServiceNowAssessmentServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceNowAssessmentServerApplication.class, args);
	}

	@Bean
	WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS");
			}
		};
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
