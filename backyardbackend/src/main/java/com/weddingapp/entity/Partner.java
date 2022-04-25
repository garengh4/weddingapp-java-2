package com.weddingapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "partner")
public class Partner {
  @Id
  @Column(name = "partner_email_id")
  private String partnerEmailId;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "password")
  private String password;

  // backyard table have partner_id
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "partner_email_id")
  private List<Backyard> backyards;
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

  public List<Backyard> getBackyards() {
    return backyards;
  }

  public void setBackyards(List<Backyard> backyards) {
    this.backyards = backyards;
  }


}
