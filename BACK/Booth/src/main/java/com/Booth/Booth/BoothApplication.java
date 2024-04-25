package com.Booth.Booth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Runs the server as a Spring Boot App.
@SpringBootApplication
public class BoothApplication {

/*	@Value("${server.port}")
	public static Long SERVER_PORT;*/

	public static void main(String[] args) {
		SpringApplication.run(BoothApplication.class, args);
		// If no errors on start up it will print this. Sever port 5000 declared in app.prop.
		System.out.println("Booth Backend Running at http://127.0.0.1:5000" /*+ SERVER_PORT*/);
	}

}
