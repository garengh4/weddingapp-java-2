package com.weddingapp.api;

import java.util.List;

import javax.validation.constraints.Pattern;

import com.weddingapp.dto.BackyardDTO;
import com.weddingapp.dto.PartnerDTO;
import com.weddingapp.exception.BackyardWeddingException;
import com.weddingapp.service.PartnerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/partner-api")
public class PartnerAPI {

  @Autowired
  Environment environment;

  @Autowired
  PartnerService partnerService;

  @PostMapping(value = "/partner/register")
  public ResponseEntity<String> registerNewPartner(@RequestBody PartnerDTO partnerDTO) throws BackyardWeddingException {
    String registeredWithEmailId = partnerService.registerNewPartner(partnerDTO);
    registeredWithEmailId = environment.getProperty("PartnerAPI.REGISTRATION_SUCCESS") + registeredWithEmailId;
    return new ResponseEntity<>(registeredWithEmailId, HttpStatus.CREATED);
  }

  @GetMapping(value = "/partner/getall")
  public ResponseEntity<List<PartnerDTO>> getAllPartner() throws BackyardWeddingException {
    List<PartnerDTO> partners = partnerService.getAllPartner();
    return new ResponseEntity<>(partners, HttpStatus.OK);
  }

  @PostMapping(value = "/partner/authenticate")
  public ResponseEntity<PartnerDTO> authenticatePartner(@RequestBody PartnerDTO partnerDTO)
      throws BackyardWeddingException {
    PartnerDTO dto = partnerService.authenticatePartner(partnerDTO.getPartnerEmailId(), partnerDTO.getPassword());
    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  @DeleteMapping(value = "/partner/{partnerEmailId:.+}/delete")
  public ResponseEntity<String> deletePartnerById(
      @Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+", message = "{invalid.email.format}") @PathVariable(name = "partnerEmailId") String partnerEmailId)
      throws BackyardWeddingException {
    String deleteWithEmailId = partnerService.deletePartner(partnerEmailId);
    deleteWithEmailId = environment.getProperty("PartnerAPI.DELETE_PARTNER_SUCCESS") + deleteWithEmailId;
    return new ResponseEntity<>(deleteWithEmailId, HttpStatus.OK);
  }

  // ==================================================================================================================
  @PostMapping(value = "/backyard/{partnerEmailId:.+}/add")
  public ResponseEntity<String> addBackyardByPartnerId(
      @Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+", message = "{invalid.email.format}") @PathVariable(name = "partnerEmailId") String partnerEmailId,
      @RequestBody BackyardDTO backyardDTO) throws BackyardWeddingException {

    Integer newBackyardId = partnerService.addBackyardToPartner(partnerEmailId, backyardDTO);
    String successMsg = environment.getProperty("PartnerAPI.ADD_BACKYARD_SUCCESS") + newBackyardId;
    return new ResponseEntity<>(successMsg, HttpStatus.CREATED);
  }

  @GetMapping(value = "/backyard/{partnerEmailId:.+}/getall")
  public ResponseEntity<List<BackyardDTO>> getBackyardsByPartnerId(
      @Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+", message = "{invalid.email.format}") @PathVariable(name = "partnerEmailId") String partnerEmailId)
      throws BackyardWeddingException {
    List<BackyardDTO> backyardDTOs = partnerService.getPartnerBackyards(partnerEmailId);
    return new ResponseEntity<>(backyardDTOs, HttpStatus.OK);
  }

  @DeleteMapping(value = "/backyard/{partnerEmailId:.+}/delete/{backyardId}")
  public ResponseEntity<String> deleteBackyardById(     
      @Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+", message = "{invalid.email.format}") @PathVariable(name = "partnerEmailId") String partnerEmailId,
      @PathVariable(name = "backyardId")Integer backyardId) throws BackyardWeddingException {

  Integer deletedBackyardId = partnerService.deletePartnerBackyard(partnerEmailId, backyardId);
  String successMsg = environment.getProperty("PartnerAPI.DELETE_BACKYARD_SUCCESS") + deletedBackyardId;
  return new ResponseEntity<>(successMsg, HttpStatus.OK);
  }

  @GetMapping(value = "/backyard/getall")
  public ResponseEntity<List<BackyardDTO>> getAllBackyards() throws BackyardWeddingException {
    List<BackyardDTO> backyardDTOs = partnerService.getAllBackyards();
    return new ResponseEntity<>(backyardDTOs, HttpStatus.OK);
  }

}
