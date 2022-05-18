package com.weddingapp.dto;

public class BackyardDTO {

  private Integer backyardId;
  private String backyardName;
  private String backyardDescription;
  private String backyardCity;
  private Integer backyardCost;
  private String partnerEmailId;

  // -----------------------------------------------------------------------------------------------------------

  public Integer getBackyardId() {
    return backyardId;
  }

  public void setBackyardId(Integer backyardId) {
    this.backyardId = backyardId;
  }

  public String getBackyardName() {
    return backyardName;
  }

  public void setBackyardName(String backyardName) {
    this.backyardName = backyardName;
  }

  public String getBackyardDescription() {
    return backyardDescription;
  }

  public void setBackyardDescription(String backyardDescription) {
    this.backyardDescription = backyardDescription;
  }

  public String getBackyardCity() {
    return backyardCity;
  }

  public void setBackyardCity(String backyardCity) {
    this.backyardCity = backyardCity;
  }

  public Integer getBackyardCost() {
    return backyardCost;
  }

  public void setBackyardCost(Integer backyardCost) {
    this.backyardCost = backyardCost;
  }

  public String getPartnerEmailId() {
    return partnerEmailId;
  }

  public void setPartnerEmailId(String partnerEmailId) {
    this.partnerEmailId = partnerEmailId;
  }

}
