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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerControllerApplicationsTests {
    @InjectMocks
    private CustomerController controller;
    @Mock
    private CustomerService customerService;

    @Before
    public void setMock(){
        //controller = mock(CustomerController.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addCustomerTest(){
        Customer customer = new Customer();
        customer.setName("John");
        customer.setLastName("Doe");

        when(controller.addCustomer(customer)).thenReturn(ResponseEntity.accepted().body(customerService.addCustomer(customer)));
        ResponseEntity<String> response = controller.addCustomer(customer);
        //when(controller.addCustomer(customer)).thenReturn(ResponseEntity.ok(customerService.addCustomer(customer)));

        assertNotNull(response);
        assertEquals(response.getBody(), "John Doe");
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
        assertEquals("John",customerList.get(0).getName());
        assertEquals("Doe", customerList.get(0).getLastName());
        assertEquals("Robert", customerList.get(1).getName());
        assertEquals("Miles", customerList.get(1).getLastName());
    }

    @Test
    public void getCustomerById(){
        Customer c = new Customer();
        c.setName("John");
        c.setLastName("Doe");
        c.setId(123L);

        when(controller.getCustomerById(123L)).thenReturn(Optional.of(c));
        Customer customer = controller.getCustomerById(123L).get();

        assertNotNull(customer);
        assertEquals("John",customer.getName());
        assertEquals("Doe",customer.getLastName());
        assertEquals(123L,customer.getId());
    }

    @Test
    public void deleteCustomerTest(){
        Customer c = new Customer();
        c.setName("John");
        c.setLastName("Doe");
        c.setId(123L);

        when(controller.deleteCustomer(c.getId())).thenReturn(ResponseEntity.ok().body(c.getId().toString()));
        ResponseEntity<String> response = controller.deleteCustomer(c.getId());

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("123",response.getBody());
    }

    @Test
    public void updateCustomerTest(){
        Customer c = new Customer();
        c.setName("John");
        c.setLastName("Doe");
        c.setId(123L);

        when(controller.updateCustomer(c.getId(), c)).thenReturn(ResponseEntity.ok().body("updated " + c));
        ResponseEntity<String> response = controller.updateCustomer(c.getId(), c);

        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotEquals(response.getBody(), c.toString());
    }
}
