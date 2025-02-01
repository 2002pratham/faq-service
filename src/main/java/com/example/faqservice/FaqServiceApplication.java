package com.example.faqservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching  // Enable caching in the Spring Boot application
public class FaqServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaqServiceApplication.class, args);
	}
}
