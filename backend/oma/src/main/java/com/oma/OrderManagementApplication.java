package com.oma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import com.oma.model.User;
import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class OrderManagementApplication {

	private static Logger log = LoggerFactory.getLogger(OrderManagementApplication.class);

	public static void main(String[] args) {
		log.trace("Application is running");
		SpringApplication.run(OrderManagementApplication.class, args);

		Map<String, Object> map = new HashMap<>();
		map.put("user", new User());
	}

}
