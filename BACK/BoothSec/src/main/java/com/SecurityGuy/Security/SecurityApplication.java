package com.SecurityGuy.Security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {

		SpringApplication.run(SecurityApplication.class, args);
		System.out.println("Spring Boot Server Running http://127.0.0.1:5000");
	}

}
