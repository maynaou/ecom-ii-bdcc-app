package com.example.inventory_service;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.inventory_service.entities.Product;
import com.example.inventory_service.repository.ProductRepository;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ProductRepository productRepository) {
		return args -> {
              productRepository.save(Product.builder()
			                                .id(UUID.randomUUID().toString())
											.name("djaja")
											.price(1252.5)
											.quantite(120)
			                                .build());
			  productRepository.save(Product.builder()
			                                .id(UUID.randomUUID().toString())
											.name("djaja2")
											.price(12525.5)
											.quantite(120)
			                                .build());
			  productRepository.save(Product.builder()
			                                .id(UUID.randomUUID().toString())
											.name("djaja3")
											.price(12526.5)
											.quantite(120)
			                                .build());
			  productRepository.save(Product.builder()
			                                .id(UUID.randomUUID().toString())
											.name("djaja4")
											.price(12527.5)
											.quantite(140)
			                                .build());
		};
	}

}
