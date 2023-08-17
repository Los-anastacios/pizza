package com.pizzariaBackEnd.pizzariaBackEnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.pizzariaBackEnd.pizzariaBackEnd.Entity")
public class PizzariaBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzariaBackEndApplication.class, args);
	}

}
