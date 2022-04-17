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
@Table(name="partner")
public class Partner {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="partner_id")
  private Integer partnerId;

  @Column(name="first_name")
  private String firstName;

  @Column(name="last_name")
  private String lastName;

  @Column(name="partner_rating")
  private Integer partnerRating;

  // backyard table have partner_id
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name="partner_id")
  private List<Backyard> backyards;
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

  public List<Backyard> getBackyards() {
    return backyards;
  }

  public void setBackyards(List<Backyard> backyards) {
    this.backyards = backyards;
  }
  
}
