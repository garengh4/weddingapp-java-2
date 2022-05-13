package com.weddingapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.weddingapp.dto.BackyardDTO;
import com.weddingapp.dto.PartnerDTO;
import com.weddingapp.entity.Backyard;
import com.weddingapp.entity.Partner;
import com.weddingapp.exception.BackyardWeddingException;
import com.weddingapp.repository.BackyardRepository;
import com.weddingapp.repository.PartnerRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PartnerServiceTest {

  @Mock
  private PartnerRepository partnerRepository;

  @Mock
  private BackyardRepository backyardRepository;

  @InjectMocks
  private PartnerService partnerService = new PartnerServiceImpl();

  @Test
  public void registerNewPartnerValidTest() throws BackyardWeddingException {
    Partner partnerInDB = new Partner();
    Mockito.when(partnerRepository.save(Mockito.any())).thenReturn(partnerInDB);

    PartnerDTO newPartner = new PartnerDTO();
    newPartner.setPartnerEmailId("clairebear@gmail.com");
    newPartner.setFirstName("Claire");
    newPartner.setLastName("Bear");
    
    String returned = partnerService.registerNewPartner(newPartner);
    Assertions.assertEquals("clairebear@gmail.com", returned);
  }

  @Test
  public void getAllPartnerValidTest() throws BackyardWeddingException {
    List<Partner> partners = new ArrayList<>();
    Partner partnerOne = new Partner();
    partnerOne.setPartnerEmailId("clairebear@gmail.com");
    Partner partnerTwo = new Partner();
    partnerTwo.setPartnerEmailId("deborahyue@gmail.com");
    partners.add(partnerOne);
    partners.add(partnerTwo);
    Mockito.when(partnerRepository.findAll()).thenReturn(partners);

    List<PartnerDTO> returned = partnerService.getAllPartner();
    Assertions.assertEquals(2, returned.size());
  }

  @Test
  public void authenticatePartnerValidTest() throws BackyardWeddingException {
    Partner partner = new Partner();
    partner.setPartnerEmailId("clairebear@gmail.com");
    partner.setPassword("Claire@123");
    Mockito.when(partnerRepository.findById(Mockito.anyString())).thenReturn(Optional.of(partner));

    Assertions.assertEquals("clairebear@gmail.com", partnerService.authenticatePartner("clairebear@gmail.com", "Claire@123").getPartnerEmailId());
  }


}