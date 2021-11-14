package com.amigo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AmigoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmigoApplication.class, args);
	}
}
