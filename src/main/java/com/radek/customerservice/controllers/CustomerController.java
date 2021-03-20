package com.radek.customerservice.controllers;

import com.radek.customerservice.entity.Customer;
import com.radek.customerservice.exceptions.ResourceNotFoundException;
import com.radek.customerservice.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.WebApplicationType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/ip")
    public String ip(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    @GetMapping(value = "/customers", produces = {"application/json"})
    public ResponseEntity<List<Customer>> getCustomers() {
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @GetMapping(value = "/customers/{id}", produces = {"application/json"})
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping(value = "/customers", consumes = {"application/json"})
    public ResponseEntity<String> addCustomer(@Valid @RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.addCustomer(customer),
                HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/customers/{id}", consumes = {"application/json"})
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.removeCustomer(id));
    }

    @PutMapping(value = "/customers/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<String> updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer customer) {
        return ResponseEntity.accepted()
                .body(customerService.updateCustomer(id, customer));
    }
}