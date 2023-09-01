package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class EmployeeManagement {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagement.class, args);
	}

}
