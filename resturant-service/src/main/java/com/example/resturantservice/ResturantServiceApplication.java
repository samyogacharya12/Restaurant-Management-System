package com.example.resturantservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ResturantServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResturantServiceApplication.class, args);
	}

}
