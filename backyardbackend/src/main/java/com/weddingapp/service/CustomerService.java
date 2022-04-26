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
  CustomerDTO authenticateCustomer(Integer customerId, String firstName, String lastName) throws BackyardWeddingException;
  String deleteCustomerById(Integer customerId) throws BackyardWeddingException;

  Integer addEventByCustomerId(Integer customerId, EventDTO eventDTO) throws BackyardWeddingException;
  List<EventDTO> getEventsByCustomerId(Integer customerId) throws BackyardWeddingException;
  String deleteEventById(Integer eventId) throws BackyardWeddingException; 
  
  List<BackyardDTO> getAllBackyards() throws BackyardWeddingException;
  
  CustomerDTO getCustomerById(Integer customerId) throws BackyardWeddingException;
  EventDTO updateEvent(EventDTO eventDto) throws BackyardWeddingException;

}