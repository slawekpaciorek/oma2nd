package com.oma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
@EnableSwagger2
public class OrderManagementApplication {

	private static Logger log = LoggerFactory.getLogger(OrderManagementApplication.class);

	public static void main(String[] args) {
		log.trace("Application is running");
		SpringApplication.run(OrderManagementApplication.class, args);
	}

}
