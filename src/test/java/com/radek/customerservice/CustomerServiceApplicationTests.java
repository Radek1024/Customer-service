package com.radek.customerservice;

import com.radek.customerservice.entity.Customer;
import com.radek.customerservice.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerServiceApplicationTests {

    private CustomerService service;

    @BeforeEach
    public void setMock(){
        service = mock(CustomerService.class);
    }

    @Test
    public void add_customers_test(){
        Customer customer = new Customer();
        customer.setName("John");
        customer.setLastName("Doe");

        when(service.addCustomer(customer)).thenReturn("saved customer");
        String assumption = service.addCustomer(customer);

        assertNotNull(assumption);
        assertEquals("saved customer",assumption);
    }

    @Test
    public void get_customers_test(){
        Customer customer = new Customer();
        customer.setName("John");
        customer.setLastName("Doe");
        customer.setId(1L);

        Customer customer1 = new Customer();
        customer.setName("Steven");
        customer.setLastName("Seagull");
        customer.setId(7L);

        when(service.getCustomers()).thenReturn(Arrays.asList(customer, customer1));
        List<Customer> list = service.getCustomers();

        assertNotNull(list);
        assertEquals(2,list.size());
    }

    @Test
    public void remove_customer_test(){
        Customer customer = new Customer();
        customer.setName("John");
        customer.setLastName("Doe");
        customer.setId(1L);

        when(service.removeCustomer(1L)).thenReturn("removed customer id " + customer.getId());
        String removed = service.removeCustomer(1L);

        assertNotNull(removed);
        assertEquals("removed customer id " + customer.getId(),removed);
    }

    @Test
    public void get_customer_by_id_test(){
        Customer customer = new Customer();
        customer.setName("George");
        customer.setLastName("Orwell");
        customer.setId(11L);
        Customer customer1 = new Customer();
        customer.setName("Steven");
        customer.setLastName("Hawking");
        customer.setId(75L);

        when(service.getCustomerById(11L)).thenReturn(customer);
        when(service.getCustomerById(75L)).thenReturn(customer1);
        Customer c = service.getCustomerById(11L);
        Customer c1 = service.getCustomerById(75L);

        assertNotNull(c);
        assertNotNull(c1);
        assertEquals(c.getName(),customer.getName());
        assertEquals(c.getLastName(),customer.getLastName());
        assertEquals(c1.getName(),customer1.getName());
        assertEquals(c1.getLastName(),customer1.getLastName());
    }

    @Test
    public void update_customer_test(){
        Customer customer = new Customer();
        customer.setName("Harry");
        customer.setLastName("Angel");
        customer.setId(123L);

        when(service.updateCustomer(123L,customer)).thenReturn("Henry");
        String newCustomer = service.updateCustomer(123L,customer);

        assertNotNull(newCustomer);
        assertEquals(newCustomer,"Henry");
        assertNotEquals(customer.getName(),newCustomer);
    }

}
