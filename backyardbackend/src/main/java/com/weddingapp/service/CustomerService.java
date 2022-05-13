package com.weddingapp.service;

import java.util.List;

import com.weddingapp.dto.BackyardDTO;
import com.weddingapp.dto.CustomerDTO;
import com.weddingapp.dto.EventDTO;
import com.weddingapp.exception.BackyardWeddingException;

import org.springframework.stereotype.Component;

@Component
public interface CustomerService {
  // no modifiers because all sub-class in same package.

  String registerNewCustomer(CustomerDTO customerDTO) throws BackyardWeddingException;
  List<CustomerDTO> getAllCustomer() throws BackyardWeddingException;
  CustomerDTO authenticateCustomer(String emailId, String password) throws BackyardWeddingException;
  String deleteCustomer(String customerEmailId) throws BackyardWeddingException;

  Integer addEventToCustomer(String customerEmailId, EventDTO eventDTO) throws BackyardWeddingException; // returns new eventId
  List<EventDTO> getCustomerEvents(String customerEmailId) throws BackyardWeddingException;
  Integer deleteCustomerEvent(String customerEmailId, Integer eventId) throws BackyardWeddingException; 
  
  List<BackyardDTO> getAllBackyards() throws BackyardWeddingException;
  CustomerDTO getCustomerById(Integer customerId) throws BackyardWeddingException;
  EventDTO updateEvent(EventDTO eventDto) throws BackyardWeddingException;

}