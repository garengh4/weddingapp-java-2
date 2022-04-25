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

  // ============================================================================================================================
  // Testing partner functions
  @Test
  public void authenticatePartnerValidTest() throws BackyardWeddingException {
    Partner partnerInDB = new Partner();
    // partnerInDB.setPartnerId(1);
    partnerInDB.setFirstName("Deborah");
    partnerInDB.setLastName("Yue");
    List<Backyard> listOfBackyards = new ArrayList<>(); // BUG: need to initialize
    partnerInDB.setBackyards(listOfBackyards);
    Mockito.when(partnerRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(partnerInDB));

    Integer userId = 1;
    String userFirstName = "Deborah";
    String userLastName = "Yue";

    PartnerDTO partnerDTO = partnerService.authenticatePartner(userId, userFirstName, userLastName);
    Assertions.assertEquals("Deborah", partnerDTO.getFirstName());
  }

  @Test
  public void registerNewPartnerValidTest() throws BackyardWeddingException {
    Partner partnerContainer = new Partner();
    // partnerContainer.setPartnerId(99);
    Mockito.when(partnerRepository.save(Mockito.any())).thenReturn(partnerContainer);

    PartnerDTO newPartner = new PartnerDTO();
    newPartner.setFirstName("Claire");
    newPartner.setLastName("Bear");

    Integer returned = partnerService.registerNewPartner(newPartner);
    Assertions.assertEquals(99, returned);
  }

  @Test
  public void getAllPartnerValidTest() throws BackyardWeddingException {
    List<Partner> listOfPartnersInDB = new ArrayList<>();
    Partner partnerOne = new Partner();
    partnerOne.setFirstName("Deborah");
    partnerOne.setLastName("Yue");
    Partner partnerTwo = new Partner();
    partnerTwo.setFirstName("Claire");
    partnerTwo.setLastName("Bear");
    listOfPartnersInDB.add(partnerOne);
    listOfPartnersInDB.add(partnerTwo);
    Mockito.when(partnerRepository.findAll()).thenReturn(listOfPartnersInDB);

    List<PartnerDTO> returned = partnerService.getAllPartner();
    Assertions.assertEquals(2, returned.size());
  }

  @Test
  public void deletePartnerByIdValidTest() throws BackyardWeddingException {
    Partner partnerInDB = new Partner();
    // partnerInDB.setPartnerId(1);
    partnerInDB.setFirstName("Deborah");
    partnerInDB.setLastName("Yue");
    Mockito.when(partnerRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(partnerInDB));

    String returned = partnerService.deletePartnerById(1);
    Assertions.assertEquals("SERVICE: partner deleted successfully.", returned);
  }

  // ============================================================================================================================
  // Testing backyard functions
  @Test
  public void getBackyardsByPartnerIdValidTest() throws BackyardWeddingException {
    List<Backyard> listOfBackyards = new ArrayList<>();
    Backyard backyardOne = new Backyard();
    backyardOne.setBackyardCity("Kelowna");
    backyardOne.setBackyardDescription("cabin by the lake");
    Backyard backyardTwo = new Backyard();
    backyardTwo.setBackyardCity("Whitby");
    backyardTwo.setBackyardDescription("wits and drinks aplenty");
    listOfBackyards.add(backyardOne);
    listOfBackyards.add(backyardTwo);
    Partner partnerInDB = new Partner();
    // partnerInDB.setPartnerId(1);
    partnerInDB.setBackyards(listOfBackyards);
    Optional<Partner> optionalPartner = Optional.of(partnerInDB);

    Mockito.when(partnerRepository.findById(Mockito.anyInt())).thenReturn(optionalPartner);
    List<BackyardDTO> returned = partnerService.getBackyardsByPartnerId(1);
    Assertions.assertEquals(2, returned.size());
  }

  @Test
  public void addBackyardByPartnerIdValidTest() throws BackyardWeddingException {
    Backyard backyardOne = new Backyard();
    backyardOne.setBackyardId(20);
    backyardOne.setBackyardDescription("resulting backyard upon backyard.save");
    Mockito.when(backyardRepository.save(Mockito.any())).thenReturn(backyardOne);

    Partner partnerInDB = new Partner();
    // partnerInDB.setPartnerId(99);
    partnerInDB.setFirstName("resulting partner upon partnerRepository.findById");
    List<Backyard> listOfBackyards = new ArrayList<>();
    listOfBackyards.add(backyardOne);
    partnerInDB.setBackyards(listOfBackyards);
    Mockito.when(partnerRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(partnerInDB));

    BackyardDTO newBackyardDTO = new BackyardDTO();
    newBackyardDTO.setBackyardCity("Narnia");
    newBackyardDTO.setBackyardDescription("turkish delights for the groom");
    newBackyardDTO.setPartnerId(54);

    Integer returned = partnerService.addBackyardByPartnerId(99, newBackyardDTO);
    Assertions.assertEquals(20, returned);

  }

  @Test
  public void deleteBackyardByIdValidTest() throws BackyardWeddingException {
    Backyard backyardInDB = new Backyard();
    backyardInDB.setBackyardId(1);
    backyardInDB.setBackyardCity("Kelowna");
    backyardInDB.setBackyardDescription("cabin by the lake");
    Mockito.when(backyardRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(backyardInDB));

    String returned = partnerService.deleteBackyardById(backyardInDB.getBackyardId());
    Assertions.assertEquals("SERVICE: backyard removed successfully.", returned);
  }

}