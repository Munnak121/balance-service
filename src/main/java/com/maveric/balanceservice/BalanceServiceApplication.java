package com.maveric.balanceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;


@EnableMongoAuditing
@SpringBootApplication
public class BalanceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BalanceServiceApplication.class, args);
	}

}
