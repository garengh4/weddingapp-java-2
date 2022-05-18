package com.weddingapp.service;

import java.util.List;

import com.weddingapp.dto.BackyardDTO;
import com.weddingapp.dto.PartnerDTO;
import com.weddingapp.exception.BackyardWeddingException;

import org.springframework.stereotype.Component;

@Component
public interface PartnerService {

  String registerNewPartner(PartnerDTO partnerDTO) throws BackyardWeddingException; //returns newly added partnerId
  List<PartnerDTO> getAllPartner() throws BackyardWeddingException;
  PartnerDTO authenticatePartner(String emailId, String password) throws BackyardWeddingException;
  String deletePartner(String partnerEmailId) throws BackyardWeddingException;

  Integer addBackyardToPartner(String partnerEmailId, BackyardDTO backyardDTO) throws BackyardWeddingException; //returns newly added backyardId.
  List<BackyardDTO> getPartnerBackyards(String partnerEmailId) throws BackyardWeddingException;
  List<BackyardDTO> getAllBackyards() throws BackyardWeddingException;
  Integer deletePartnerBackyard(String partnerEmailId, Integer backyardId) throws BackyardWeddingException; //returns success message
}
