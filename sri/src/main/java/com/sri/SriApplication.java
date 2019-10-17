package com.sri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/*
 * The entrance of the application
 * */
@EnableScheduling
@SpringBootApplication
public class SriApplication {

	public static void main(String[] args) {
		SpringApplication.run(SriApplication.class, args);
	}

}
