package com.example.inventory_service;

import java.util.List;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;

import com.example.inventory_service.entities.Product;
import com.example.inventory_service.repository.ProductRepository;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ProductRepository productRepository, StreamBridge streamBridge) {
		return args -> {
			  Product product1 = Product.builder()
			                                .id(UUID.randomUUID().toString())
											.name("djaja")
											.price(1252.5)
											.quantity(120)
			                                .build();
              productRepository.save(product1);

			  streamBridge.send("inventoryProducer-out-0", product1);


			  Product product2 = Product.builder()
			                                .id(UUID.randomUUID().toString())
											.name("djaja2")
											.price(12525.5)
											.quantity(120)
			                                .build();
			  productRepository.save(product2);

			  streamBridge.send("inventoryProducer-out-0", product2);


			  Product product3 = Product.builder()
			                                .id(UUID.randomUUID().toString())
											.name("djaja3")
											.price(12526.5)
											.quantity(120)
			                                .build();
			  productRepository.save(product3);

			  streamBridge.send("inventoryProducer-out-0", product3);

			  Product product4 = Product.builder()
			                                .id(UUID.randomUUID().toString())
											.name("djaja4")
											.price(12527.5)
											.quantity(140)
			                                .build();
			  productRepository.save(product4);

			  streamBridge.send("inventoryProducer-out-0", product4);

		};
	}

}
