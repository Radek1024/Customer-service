package com.radek.customerservice.services;

import com.radek.customerservice.entity.Customer;
import com.radek.customerservice.exceptions.ResourceAlreadyExists;
import com.radek.customerservice.exceptions.ResourceNotFoundException;
import com.radek.customerservice.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    @Qualifier("customer")
    private final CustomerRepository customerRepository;

    public String addCustomer(Customer customer) {
        if (customerRepository.existsByNameAndLastName(customer.getName(), customer.getLastName())) {
            throw new ResourceAlreadyExists(customer.getLastName());
        }
        customerRepository.save(customer);
        return String.format("Saved %s.", customer);
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll()
                .forEach(customers::add);
        return customers.isEmpty() ? Collections.emptyList() : customers;
    }

    public String removeCustomer(Long id) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(id));
        customerRepository.deleteById(id);

        return String.format("Removed %s ", c);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(id));
    }

    public String updateCustomer(Long id, Customer oldCustomer) {
            Customer newCustomer = customerRepository.findById(id)
                    .orElseThrow(()->new ResourceNotFoundException(id));
            newCustomer.setName(oldCustomer.getName());
            newCustomer.setLastName(oldCustomer.getLastName());
            newCustomer.setEmail(oldCustomer.getEmail());
            newCustomer.setCity(oldCustomer.getCity());
            newCustomer.setAddress(oldCustomer.getAddress());
            customerRepository.save(newCustomer);

            return String.format("Updated %s", newCustomer);
    }
}
