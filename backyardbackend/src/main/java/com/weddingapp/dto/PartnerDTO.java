package com.weddingapp.dto;

import java.util.List;

public class PartnerDTO {

  private Integer partnerId;
  private String firstName;
  private String lastName;
  private String password;
  private String emailId;
  private List<BackyardDTO> partnerBackyards;
  // -----------------------------------------------------------------------------------------------------------

  public Integer getPartnerId() {
    return partnerId;
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

  public void setPartnerId(Integer partnerId) {
    this.partnerId = partnerId;
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

  public List<BackyardDTO> getPartnerBackyards() {
    return partnerBackyards;
  }

  public void setPartnerBackyards(List<BackyardDTO> partnerBackyards) {
    this.partnerBackyards = partnerBackyards;
  }

}
