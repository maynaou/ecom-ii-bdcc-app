package com.example.billing_service.service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.example.billing_service.model.Customer;
import com.example.billing_service.model.Product;

@Service
public class KafkaDataService {

    private final Map<Long, Customer> customers = new ConcurrentHashMap<>();
    private final Map<String, Product> products = new ConcurrentHashMap<>();

    public void handleCustomer(Customer customer) {
        if (customer != null && customer.getId() != null) {
            customers.put(customer.getId(), customer);
        }
    }

    public void handleProduct(Product product) {
        if (product != null && product.getId() != null) {
            products.put(product.getId(), product);
        }
    }

    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }

    public Collection<Product> getAllProducts() {
        return products.values();
    }

    public Customer getCustomer(Long id) {
        return customers.get(id);
    }

    public Product getProduct(String id) {
        return products.get(id);
    }
}
