package com.example.poem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PoemApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoemApplication.class, args);
	}
}
