package com.weddingapp.api;

import java.util.List;

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

  // @DeleteMapping(value = "/delete/{partnerId}")
  // public ResponseEntity<String> deletePartnerById(@PathVariable(name =
  // "partnerId") Integer partnerId)
  // throws BackyardWeddingException {
  // String successMsg = partnerService.deletePartnerById(partnerId);
  // return new ResponseEntity<>(successMsg, HttpStatus.OK);
  // }

  // @PostMapping(value = "/addbackyard/{partnerId}")
  // public ResponseEntity<String> addBackyardByPartnerId(@PathVariable(name =
  // "partnerId") Integer partnerId,
  // @RequestBody BackyardDTO backyardDTO) throws BackyardWeddingException {
  // Integer newBackyardId = partnerService.addBackyardByPartnerId(partnerId,
  // backyardDTO);
  // String successMsg = "New backyard added with new backyardId: " +
  // newBackyardId;
  // return new ResponseEntity<>(successMsg, HttpStatus.CREATED);
  // }

  // @GetMapping(value = "/getallbackyards/{partnerId}")
  // public ResponseEntity<List<BackyardDTO>>
  // getBackyardsByPartnerId(@PathVariable(name = "partnerId") Integer partnerId)
  // throws BackyardWeddingException {
  // List<BackyardDTO> list = partnerService.getBackyardsByPartnerId(partnerId);
  // return new ResponseEntity<>(list, HttpStatus.OK);
  // }

  // @DeleteMapping(value = "/deletebackyard/{backyardId}")
  // public ResponseEntity<String> deleteBackyardById(@PathVariable(name =
  // "backyardId") Integer backyardId)
  // throws BackyardWeddingException {
  // String successMsg = partnerService.deleteBackyardById(backyardId);
  // return new ResponseEntity<>(successMsg, HttpStatus.OK);
  // }

}
