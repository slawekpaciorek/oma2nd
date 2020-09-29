package com.oma;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
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
