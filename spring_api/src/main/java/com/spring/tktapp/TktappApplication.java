package com.spring.tktapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.spring.tktapp.application.controller"})
@EnableJpaRepositories("com.spring.tktapp.domain.repositories")
//@ComponentScan("com.spring.tktapp.domain.service")
@EntityScan("com.spring.tktapp.application.entity")
public class TktappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TktappApplication.class, args);
//		SpringApplication.run(TktappApplicationConfig.class, args);
	}
}
