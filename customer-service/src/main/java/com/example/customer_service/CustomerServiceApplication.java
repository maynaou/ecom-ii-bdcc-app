package com.example.customer_service;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.customer_service.repository.CustomerRepository;
import com.example.customer_service.entities.Customer;


@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRespository) {
		return args ->  {

		    Stream.of("maynaou","ronaldo","yassin","momo","dodo").forEach((n) -> {
				   	Customer customer = Customer.builder()
			                    .name(n)
								.email(n + "@gmail.com")
			                    .build();
			        customerRespository.save(customer);   
			});

		};
	}

}
