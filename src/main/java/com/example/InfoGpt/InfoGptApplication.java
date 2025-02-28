package com.example.InfoGpt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.InfoGpt.Repositories")
public class InfoGptApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfoGptApplication.class, args);
	}

}
