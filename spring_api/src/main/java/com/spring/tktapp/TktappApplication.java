package com.spring.tktapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class TktappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TktappApplication.class, args);
		SpringApplication.run(TktappApplicationConfig.class, args);
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testRest() {
		return "rest tested";
	}
}
