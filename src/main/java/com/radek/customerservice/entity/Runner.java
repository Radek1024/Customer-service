package com.radek.customerservice.entity;

import com.radek.customerservice.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {
    private final CustomerService customerService;

    @Override
    public void run(String... args) throws Exception {
        Customer c = new Customer();
        c.setName("John");
        c.setLastName("Deer");
        c.setEmail("j.deer@forest.com");

        Customer c1 = new Customer();
        c1.setName("C.");
        c1.setLastName("J.");
        c1.setEmail("c.j@hood.com");

        customerService.addCustomer(c);
        customerService.addCustomer(c1);

    }
}
