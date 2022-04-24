package com.weddingapp.repository;

import com.weddingapp.entity.Customer;
import com.weddingapp.exception.BackyardWeddingException;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
  // Primary key is customerId = Integer

  Customer findByEmailId(String emailid) throws BackyardWeddingException;
}
