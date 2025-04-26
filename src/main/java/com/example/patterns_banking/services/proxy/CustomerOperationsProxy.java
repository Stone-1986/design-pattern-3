package com.example.patterns_banking.services.proxy;

import com.example.patterns_banking.dtos.CustomerDTO;
import com.example.patterns_banking.models.Customer;
import com.example.patterns_banking.repositories.ICustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomerOperationsProxy implements ICustomerOperations {
    private final ICustomerRepository customerRepository;

    public CustomerOperationsProxy(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer create(CustomerDTO customerDTO) {
        String email = customerDTO.getEmail().toLowerCase();
        if (email.endsWith("@yahoo.com") ||
            email.endsWith("@yahoo.es") ||
            email.matches(".*@yahoo\\.[a-z]{2,3}$")
        ) {
            throw new IllegalArgumentException("No se permiten correos de dominio Yahoo");
        }
        Customer customer = Customer
                .builder()
                .name(customerDTO.getName())
                .email(customerDTO.getEmail())
                .build();
        return customerRepository.save(customer);
    }
}
