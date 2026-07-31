package com.example.billing_service;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.example.billing_service.entities.Bill;
import com.example.billing_service.entities.ProductItem;
import com.example.billing_service.model.Customer;
import com.example.billing_service.model.Product;
import com.example.billing_service.repository.BillRepository;
import com.example.billing_service.repository.ProductItemRepository;
import com.example.billing_service.service.KafkaDataService;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	// @Bean
	CommandLineRunner commandLineRunner(BillRepository billRepository, ProductItemRepository productItemRepository,
			KafkaDataService kafkaDataService) {

		return args -> {

			while (kafkaDataService.getAllCustomers().isEmpty()
					|| kafkaDataService.getAllProducts().isEmpty()) {
				System.out.println("++++");

				Thread.sleep(100);
			}
			Collection<Customer> customers = kafkaDataService.getAllCustomers();

			System.out.println(customers);
			Collection<Product> products = kafkaDataService.getAllProducts();
			System.out.println(products);



			customers.forEach((c) -> {
				Bill bill = Bill.builder()
						.customerId(c.getId())
						.billingDate(new Date())
						.build();
				billRepository.save(bill);

				products.forEach((p) -> {
					ProductItem productItem = ProductItem.builder()
							.bill(bill)
							.productId(p.getId())
							.quantity(1 + new Random().nextInt(10))
							.unitPrice(p.getPrice())
							.build();
					productItemRepository.save(productItem);
				});
			});

		};
	}

}
