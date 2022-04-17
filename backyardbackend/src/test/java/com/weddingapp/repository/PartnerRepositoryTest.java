package com.weddingapp.repository;

import java.util.Optional;

import com.weddingapp.entity.Partner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PartnerRepositoryTest {

  @Autowired
  private PartnerRepository partnerRepository;

  private Partner partner;

  @BeforeEach
  public void setUp() {
  partner = new Partner();
  partner.setFirstName("Deborah");
  partner.setLastName("Yue");
  }

  @Test
  public void savePartnerValidTest() {
    Partner partnerFromDB = partnerRepository.save(partner);   
    // Assertions.assertEquals(1, partnerFromDB.getPartnerId());
    Assertions.assertEquals("Yue", partnerFromDB.getLastName());
  }

  @Test
  public void findByIdValidTest() {
    partnerRepository.save(partner);
    Optional<Partner> partnerContainer = partnerRepository.findById(1);
    Assertions.assertTrue(partnerContainer.isPresent());
  }

  // partner hasn't been saved so it should be empty.
  @Test
  public void findByIdInvalidTest() {
    Optional<Partner> partnerContainer = partnerRepository.findById(1);
    Assertions.assertTrue(partnerContainer.isEmpty());

  }

}