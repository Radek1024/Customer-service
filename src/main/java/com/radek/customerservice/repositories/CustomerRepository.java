package com.radek.customerservice.repositories;

import com.radek.customerservice.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("customer")
public interface CustomerRepository extends CrudRepository<Customer,Long> {
    boolean existsByNameAndLastName(String name, String lastName);
}
