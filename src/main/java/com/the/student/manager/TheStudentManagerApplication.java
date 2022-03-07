package com.the.student.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.the.student.manager"})
public class TheStudentManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheStudentManagerApplication.class, args);
	}
}