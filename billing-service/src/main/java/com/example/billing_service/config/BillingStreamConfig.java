package com.example.billing_service.config;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.billing_service.model.Customer;
import com.example.billing_service.model.Product;
import com.example.billing_service.service.KafkaDataService;

@Configuration
public class BillingStreamConfig {

    @Bean
    public Consumer<Customer> customerConsumer(KafkaDataService kafkaDataService) {
        return customer -> {
            kafkaDataService.handleCustomer(customer);
            System.out.println("Reçu event client Kafka : " + customer);
        };
    }


    @Bean
    public Consumer<Product> inventoryConsumer(KafkaDataService kafkaDataService) {
        return product -> {
            kafkaDataService.handleProduct(product);
            System.out.println("Reçu event produit Kafka : " + product);
        };
    }
}
