package com.radek.customerservice.controllers;

import com.radek.customerservice.entity.Customer;
import com.radek.customerservice.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/ip")
    public String ip(HttpServletRequest request){
        return request.getRemoteAddr();
    }   

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomers(){
        if (customerService.getCustomers().isEmpty()){
            return ResponseEntity.notFound()
                    .build();
        }
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @PostMapping("/customers")
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer){
        return ResponseEntity.accepted()
                .body(customerService.addCustomer(customer));
    }

    @DeleteMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable Long id){
        return customerService
                .removeCustomer(id);
    }

    @PutMapping("/customers/{id}")
    public String updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
        customerService.updateCustomer(id,customer);
        return String.format("Updated %s.",customer);
    }
}
