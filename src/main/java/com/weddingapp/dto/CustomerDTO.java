package com.weddingapp.dto;

import java.util.List;

public class CustomerDTO {

  private String customerEmailId;
  private String firstName;
  private String lastName;
  private String password;
  private List<EventDTO> customerEvents;

  // -----------------------------------------------------------------------------------------------------------

  public String getCustomerEmailId() {
    return customerEmailId;
  }

  public void setCustomerEmailId(String customerEmailId) {
    this.customerEmailId = customerEmailId;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<EventDTO> getCustomerEvents() {
    return customerEvents;
  }

  public void setCustomerEvents(List<EventDTO> customerEvents) {
    this.customerEvents = customerEvents;
  }

}