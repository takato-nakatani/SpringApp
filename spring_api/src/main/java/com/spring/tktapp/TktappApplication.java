package com.spring.tktapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//ここで指定したパッケージ以下のものしかスキャンされない。(アノテーションでBeanを自動作成できない)
@SpringBootApplication(scanBasePackages = {"com.spring"})
//以下でも可
//@SpringBootApplication(scanBasePackages = {
//		"com.spring.tktapp.application.controller",
//		"com.spring.tktapp.domain.service"
//})
@EnableJpaRepositories("com.spring.tktapp.domain.repositories")
@EntityScan("com.spring.tktapp.application.entity")
public class TktappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TktappApplication.class, args);
		SpringApplication.run(TktappApplicationConfig.class, args);
	}
}
