package com.server.bayztracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BayztrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BayztrackerApplication.class, args);
	}
}
