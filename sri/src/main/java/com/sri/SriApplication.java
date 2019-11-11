package com.sri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/*
 * The entrance of the application
 * */
@EnableScheduling
@EnableAsync
@SpringBootApplication
public class SriApplication {

	public static void main(String[] args) {
		SpringApplication.run(SriApplication.class, args);
	}
}
