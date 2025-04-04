package com.washzone.carwash.userservice.repository;

import com.washzone.carwash.userservice.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {
}
