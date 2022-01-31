package com.example.compte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CompteApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompteApplication.class, args);
	}

}
