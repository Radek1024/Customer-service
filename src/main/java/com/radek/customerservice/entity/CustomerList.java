package com.radek.customerservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class CustomerList {
    private List<Customer> customers;
}
