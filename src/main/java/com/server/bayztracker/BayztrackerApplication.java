package com.server.bayztracker;

import com.server.bayztracker.dao.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BayztrackerApplication {

	@Autowired
	ExpenseRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(BayztrackerApplication.class, args);
	}
}
