package com.example.hawking_bros;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class HawkingBrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(HawkingBrosApplication.class, args);
	}

}
