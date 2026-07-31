package com.example.billing_service.web;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.billing_service.entities.Bill;
import com.example.billing_service.entities.ProductItem;
import com.example.billing_service.feign.CustomerRestClient;
import com.example.billing_service.feign.ProductRestClient;
import com.example.billing_service.model.Customer;
import com.example.billing_service.model.Product;
import com.example.billing_service.repository.BillRepository;
import com.example.billing_service.repository.ProductItemRepository;
import com.example.billing_service.service.KafkaDataService;

@RestController
public class BillRestController {
       BillRepository billRepository;
       ProductItemRepository productItemRepository;
       CustomerRestClient customerRestClient;
       ProductRestClient productRestClient;

       KafkaDataService kafkaDataService;

       public BillRestController(BillRepository billRepository, ProductItemRepository productItemRepository
              , CustomerRestClient customerRestClient, ProductRestClient productRestClient, KafkaDataService kafkaDataService
       ){
              this.billRepository = billRepository;
              this.productItemRepository = productItemRepository;
              this.customerRestClient = customerRestClient;
              this.productRestClient = productRestClient;
              this.kafkaDataService = kafkaDataService;
       }

       @GetMapping("/bills/{id}")
       public Bill getBill(@PathVariable Long id) {
              Bill bill = billRepository.findById(id).get();
              bill.setCustomer(kafkaDataService.getCustomer(bill.getCustomerId()));
              bill.getProductItems().forEach(productItem -> {
                      productItem.setProduct(kafkaDataService.getProduct(productItem.getProductId()));
              });
              return bill;
       }

       @PostMapping("/bills/generate")
       public void generateAllBills() {

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
       } 
}
