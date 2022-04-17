package com.weddingapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {
  
	@Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="customer_id")
	private Integer customerId;

  @Column(name="first_name")
	private String firstName;

  @Column(name="last_name")
	private String lastName;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name="customer_id")
  private List<Event> events;

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

  public List<Event> getEvents() {
    return events;
  }

  public void setEvents(List<Event> events) {
    this.events = events;
  }


}