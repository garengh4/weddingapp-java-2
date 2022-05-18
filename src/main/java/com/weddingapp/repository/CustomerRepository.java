package com.weddingapp.repository;

import com.weddingapp.entity.Customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {
  // Primary key is customer_email_id = String

  // Customer findByEmailId(String emailid) throws BackyardWeddingException;
}
