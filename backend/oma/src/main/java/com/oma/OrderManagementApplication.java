package com.oma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderManagementApplication {

	private static Logger log = LoggerFactory.getLogger(OrderManagementApplication.class);

	public static void main(String[] args) {
		log.trace("Application is running");
		SpringApplication.run(OrderManagementApplication.class, args);
	}



}
