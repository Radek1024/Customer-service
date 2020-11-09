package com.radek.customerservice;

import com.radek.customerservice.entity.Customer;
import com.radek.customerservice.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
class CustomerServiceApplicationTests {

    private CustomerService service;

    @BeforeEach
    public void setMock(){
        service = mock(CustomerService.class);
    }

    @Test
    public void testingServiceGetCustomers(){
        List<Customer> list = service.getCustomers();
        assertNotNull(list);
    }

}
