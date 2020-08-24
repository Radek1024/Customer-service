package com.radek.customerservice.services;

import com.radek.customerservice.entity.Customer;
import com.radek.customerservice.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    public String addCustomer(Customer customer){
        if (customerRepository.existsByNameAndLastName(customer.getName(),customer.getLastName())){
            return String.format("%s is already saved.", customer);
        }
        customerRepository.save(customer);
        return String.format("Saved %s.",customer);

    }

    public List<Customer> getCustomers(){
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll()
                .forEach(customers::add);
        return customers.isEmpty() ? Collections.emptyList() : customers;
    }

    public String removeCustomer(Long id){
        if(customerRepository.existsById(id)) {
            customerRepository.deleteById(id);

            return String.format("Removed %s ", customerRepository.findById(id));
        }
        return String.format("Customer %d not found.", id);
    }

    public Optional<Customer> getCustomerById(Long id){
        return customerRepository.findById(id);
    }

    public void updateCustomer(Long id, Customer oldCustomer){
        Customer newCustomer = customerRepository.findById(id).get();
        newCustomer.setName(oldCustomer.getName());
        newCustomer.setLastName(oldCustomer.getLastName());
        newCustomer.setEmail(oldCustomer.getEmail());
        customerRepository.save(newCustomer);
    }
}
