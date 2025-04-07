package com.washzone.carwash.userservice.service;

import com.washzone.carwash.userservice.dto.CustomerDto;
import com.washzone.carwash.userservice.model.CustomerModel;
import com.washzone.carwash.userservice.model.UserModel;
import com.washzone.carwash.userservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public void save(UserModel user, CustomerDto customerDto) {
        CustomerModel customerModel = new CustomerModel(customerDto.getName(),customerDto.getEmail());
        customerModel.setUser(user);
        customerRepository.save(customerModel);
    }
}
