package com.weddingapp.dto;

import java.util.List;

public class CustomerDTO {

  private Integer customerId;
  private String firstName;
  private String lastName;
  private List<EventDTO> customerEvents;
  
  // -----------------------------------------------------------------------------------------------------------
  public Integer getCustomerId() {
    return customerId;
  }
  public void setCustomerId(Integer customerId) {
    this.customerId = customerId;
  }
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public List<EventDTO> getCustomerEvents() {
    return customerEvents;
  }
  public void setCustomerEvents(List<EventDTO> customerEvents) {
    this.customerEvents = customerEvents;
  }


  
}