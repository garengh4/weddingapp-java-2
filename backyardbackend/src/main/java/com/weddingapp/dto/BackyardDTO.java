package com.weddingapp.dto;

public class BackyardDTO {

  private Integer backyardId;
  private String backyardName;
  private String backyardDescription;
  private String backyardCity;
  private Integer backyardCost;
  private Integer partnerId;

  // -----------------------------------------------------------------------------------------------------------

  public Integer getBackyardId() {
    return backyardId;
  }

  public String getBackyardName() {
    return backyardName;
  }

  public void setBackyardName(String backyardName) {
    this.backyardName = backyardName;
  }

  public void setBackyardId(Integer backyardId) {
    this.backyardId = backyardId;
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

  public Integer getPartnerId() {
    return partnerId;
  }

  public void setPartnerId(Integer partnerId) {
    this.partnerId = partnerId;
  }

}
