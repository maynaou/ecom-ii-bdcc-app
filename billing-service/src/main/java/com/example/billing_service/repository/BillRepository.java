package com.example.billing_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.billing_service.entities.Bill;


@RepositoryRestResource
public interface BillRepository extends JpaRepository<Bill,Long>{
    
}
