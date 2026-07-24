package com.example.billing_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.billing_service.model.Customer;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@FeignClient(name = "customer-service")
public interface CustomerRestClient {
     @GetMapping("/api/customers/{id}")
     @CircuitBreaker(name = "customer-service", fallbackMethod = "getDefaultCustomer")
     Customer getCustomerById(@PathVariable Long id);

     @GetMapping("/api/customers")
     PagedModel<Customer> getAllCustomers();

     default Customer getDefaultCustomer(Long id, Exception ex) {
          ex.printStackTrace();
          Customer customer = Customer.builder()
                                      .id(id)
                                      .name("default customer name")
                                      .email("default@gmail.com")
                                      .build();
            return customer;
     }
}
