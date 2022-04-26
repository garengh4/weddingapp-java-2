package com.weddingapp.dto;

import java.util.List;

public class PartnerDTO {

  private String partnerEmailId;
  private String firstName;
  private String lastName;
  private String password;
  private List<BackyardDTO> partnerBackyards;
  // -----------------------------------------------------------------------------------------------------------

  public String getPartnerEmailId() {
    return partnerEmailId;
  }

  public void setPartnerEmailId(String partnerEmailId) {
    this.partnerEmailId = partnerEmailId;
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

  public List<BackyardDTO> getPartnerBackyards() {
    return partnerBackyards;
  }

  public void setPartnerBackyards(List<BackyardDTO> partnerBackyards) {
    this.partnerBackyards = partnerBackyards;
  }

}
