package com.weddingapp.repository;

import java.util.Optional;

import com.weddingapp.entity.Backyard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class BackyardRepositoryTest {
  @Autowired
  private BackyardRepository backyardRepository;

  private Backyard backyard;
  
  @BeforeEach
  public void setUp() {
    backyard = new Backyard();
    backyard.setBackyardCity("Kelowna");
    backyard.setBackyardDescription("cute cabin next to the lake");
  }

  @Test
  public void saveBackyardValidTest() {
    Backyard backyardFromDB = backyardRepository.save(backyard);
    // Assertions.assertEquals(1, backyardFromDB.getBackyardId());
    Assertions.assertEquals("Kelowna", backyardFromDB.getBackyardCity());
  }

  @Test
  public void findByIdValidTest() {
    backyardRepository.save(backyard);                                
    Optional<Backyard> backyardContainer = backyardRepository.findById(1);
    Assertions.assertTrue(backyardContainer.isPresent());
  }

  @Test
  public void findByIdInvalidTest() {
    Optional<Backyard> backyardContainer = backyardRepository.findById(1);
    Assertions.assertTrue(backyardContainer.isEmpty());

  }


}