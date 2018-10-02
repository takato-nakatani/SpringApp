package com.spring.tktapp;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TktappApplicationConfig {

    @Bean
    MyDataBean myDataBean(){
        return new MyDataBean();
    }
}
