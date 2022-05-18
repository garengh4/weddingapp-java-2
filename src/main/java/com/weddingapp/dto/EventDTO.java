package com.weddingapp.dto;

import java.time.LocalDate;

public class EventDTO {

  private Integer eventId;
  private String eventName;
  private String eventDescription;
  private LocalDate eventDate;

  // being used for adding events.
  private String customerEmailId;
  private Integer backyardId;

  // -----------------------------------------------------------------------------------------------------------

  public Integer getEventId() {
    return eventId;
  }

  public void setEventId(Integer eventId) {
    this.eventId = eventId;
  }

  public String getEventName() {
    return eventName;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }

  public String getEventDescription() {
    return eventDescription;
  }

  public void setEventDescription(String eventDescription) {
    this.eventDescription = eventDescription;
  }

  public LocalDate getEventDate() {
    return eventDate;
  }

  public void setEventDate(LocalDate eventDate) {
    this.eventDate = eventDate;
  }

  public String getCustomerEmailId() {
    return customerEmailId;
  }

  public void setCustomerEmailId(String customerEmailId) {
    this.customerEmailId = customerEmailId;
  }

  public Integer getBackyardId() {
    return backyardId;
  }

  public void setBackyardId(Integer backyardId) {
    this.backyardId = backyardId;
  }

}