package com.radek.customerservice;

import com.radek.customerservice.controllers.CustomerController;
import com.radek.customerservice.entity.Customer;
import com.radek.customerservice.services.CustomerService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerControllerApplicationsTests {
    private CustomerController controller;

    @BeforeEach
    public void setMock(){
        controller = mock(CustomerController.class);
    }

    @Test
    public void addCustomerTest(){
        Customer customer = new Customer();
        customer.setName("John");
        customer.setLastName("Doe");

        when(controller.addCustomer(customer)).thenReturn(ResponseEntity.accepted().body(customer.toString()));
        ResponseEntity<String> response = controller.addCustomer(customer);

        assertNotNull(response);
        assertEquals(response.getBody(), customer.toString());
        assertEquals(response.getStatusCode(), HttpStatus.ACCEPTED);
    }

    @Test
    public void getCustomersTest(){
        Customer customer = new Customer();
        customer.setName("John");
        customer.setLastName("Doe");
        Customer customer1 = new Customer();
        customer1.setName("Robert");
        customer1.setLastName("Miles");

        when(controller.getCustomers()).thenReturn(ResponseEntity.accepted().body(Arrays.asList(customer,customer1)));
        List<Customer> customerList = controller.getCustomers().getBody();

        assertNotNull(customerList);
        assertEquals(customerList.size(),2);
        assertEquals(customerList.get(0).getName(),"John");
        assertEquals(customerList.get(0).getLastName(),"Doe");
        assertEquals(customerList.get(1).getName(),"Robert");
        assertEquals(customerList.get(1).getLastName(),"Miles");
    }
}
