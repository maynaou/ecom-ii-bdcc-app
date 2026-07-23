package com.example.billing_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.billing_service.model.Product;

@FeignClient(name = "inventory-service")
public interface ProductRestClient {
     @GetMapping("/api/products/{id}")
     Product getProductById(@PathVariable String id);

     @GetMapping("/api/products")
     PagedModel<Product> getAllProducts();
}
