package com.weddingapp.dto;

import java.util.List;

public class CustomerDTO {

  private Integer customerId;
  private String firstName;
  private String lastName;
  private String password;
  private String emailId;
  private List<EventDTO> customerEvents;
  
  // -----------------------------------------------------------------------------------------------------------
  
  public Integer getCustomerId() {
    return customerId;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getEmailId() {
    return emailId;
  }
  public void setEmailId(String emailId) {
    this.emailId = emailId;
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