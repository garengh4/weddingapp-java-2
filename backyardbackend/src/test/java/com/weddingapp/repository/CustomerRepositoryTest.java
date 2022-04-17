package com.weddingapp.repository;

import java.util.Optional;

import com.weddingapp.entity.Customer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CustomerRepositoryTest {

  @Autowired
  private CustomerRepository customerRepository;

  private Customer customer;

  @BeforeEach
  public void setUp() {
    customer = new Customer();
    customer.setFirstName("Deborah");
    customer.setLastName("Yue");
  }

  @Test
  void saveCustomerValidTest() {
    Customer customerFromDB = customerRepository.save(customer);
    // Assertions.assertEquals(1, customerFromDB.getCustomerId());
    Assertions.assertEquals("Yue", customerFromDB.getLastName());
  }

  @Test
  public void findByIdValidTest() {
    customerRepository.save(customer);
    Optional<Customer> customerContainer = customerRepository.findById(1);
    Assertions.assertTrue(customerContainer.isPresent());
  }

  @Test
  public void findByIdInvalidTest() {
    Optional<Customer> customerContainer = customerRepository.findById(1);
    Assertions.assertTrue(customerContainer.isEmpty());
  }

}
