package com.weddingapp.dto;

import java.util.List;

public class PartnerDTO {

  private Integer partnerId;
  private String firstName;
  private String lastName;
  private Integer partnerRating;
  private List<BackyardDTO> partnerBackyards;
  // -----------------------------------------------------------------------------------------------------------

  public Integer getPartnerId() {
    return partnerId;
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
  public Integer getPartnerRating() {
    return partnerRating;
  }
  public void setPartnerRating(Integer partnerRating) {
    this.partnerRating = partnerRating;
  }
  public List<BackyardDTO> getPartnerBackyards() {
    return partnerBackyards;
  }
  public void setPartnerBackyards(List<BackyardDTO> partnerBackyards) {
    this.partnerBackyards = partnerBackyards;
  }

}
