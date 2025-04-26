package com.example.patterns_banking.services;

import com.example.patterns_banking.dtos.CustomerDTO;
import com.example.patterns_banking.models.Customer;
import com.example.patterns_banking.repositories.CustomerRepository;
import com.example.patterns_banking.repositories.ICustomerRepository;
import com.example.patterns_banking.services.proxy.ICustomerOperations;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
  private final ICustomerOperations customerOperations;

  public CustomerService(ICustomerOperations customerOperations) {
    this.customerOperations = customerOperations;
  }

  public Customer create(CustomerDTO customerDTO) {
    return customerOperations.create(customerDTO);
  }

}
