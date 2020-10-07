package com.egen.oms.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.egen.oms")
@SpringBootApplication
public class EgenEommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EgenEommerceApplication.class, args);
	}

}
