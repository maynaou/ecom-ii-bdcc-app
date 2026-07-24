package com.example.billing_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.billing_service.model.Product;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient(name = "inventory-service")
public interface ProductRestClient {
     @GetMapping("/api/products/{id}")
     @CircuitBreaker(name = "inventory-service", fallbackMethod = "getDefaultProduct")
     Product getProductById(@PathVariable String id);

     @GetMapping("/api/products")
     PagedModel<Product> getAllProducts();

     default Product getDefaultProduct(String id,Exception ex) { 
          ex.printStackTrace();
          return Product.builder().id(id).build();
     }
}
