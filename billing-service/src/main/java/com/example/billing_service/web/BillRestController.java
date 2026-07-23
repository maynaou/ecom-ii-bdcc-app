package com.example.billing_service.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.billing_service.entities.Bill;
import com.example.billing_service.feign.CustomerRestClient;
import com.example.billing_service.feign.ProductRestClient;
import com.example.billing_service.repository.BillRepository;
import com.example.billing_service.repository.ProductItemRepository;

@RestController
public class BillRestController {
       BillRepository billRepository;
       ProductItemRepository productItemRepository;
       CustomerRestClient customerRestClient;
       ProductRestClient productRestClient;

       public BillRestController(BillRepository billRepository, ProductItemRepository productItemRepository
              , CustomerRestClient customerRestClient, ProductRestClient productRestClient
       ){
              this.billRepository = billRepository;
              this.productItemRepository = productItemRepository;
              this.customerRestClient = customerRestClient;
              this.productRestClient = productRestClient;
       }

       @GetMapping("/bills/{id}")
       public Bill getBill(@PathVariable Long id) {
              Bill bill = billRepository.findById(id).get();
              bill.setCustomer(customerRestClient.getCustomerById(bill.getCustomerId()));
              bill.getProductItems().forEach(productItem -> {
                      productItem.setProduct(productRestClient.getProductById(productItem.getProductId()));
              });
              return bill;
       }
}
