package com.spring.tktapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.spring.tktapp.controller"})
@EntityScan("com.spring.tktapp.entity")
@EnableJpaRepositories("com.spring.tktapp.repositories")
public class TktappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TktappApplication.class, args);
//		SpringApplication.run(TktappApplicationConfig.class, args);
	}
}
