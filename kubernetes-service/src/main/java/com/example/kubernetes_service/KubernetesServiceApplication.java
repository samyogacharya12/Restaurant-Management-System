package com.example.kubernetes_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KubernetesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KubernetesServiceApplication.class, args);
	}

    @Bean
    public CommandLineRunner run() {
        return args -> {
            System.out.println("🚀 KubernetesServiceApplication has started!");
            // You can perform other startup logic here
        };
    }
}
