package com.weddingapp.service;

import java.util.List;

import com.weddingapp.dto.BackyardDTO;
import com.weddingapp.dto.PartnerDTO;
import com.weddingapp.exception.BackyardWeddingException;

import org.springframework.stereotype.Component;

@Component
public interface PartnerService {

  Integer registerNewPartner(PartnerDTO partnerDTO) throws BackyardWeddingException; //returns newly added partnerId
  List<PartnerDTO> getAllPartner() throws BackyardWeddingException;
  PartnerDTO authenticatePartner(Integer partnerId, String firstName, String lastName) throws BackyardWeddingException;
  String deletePartnerById(Integer partnerId) throws BackyardWeddingException;

  Integer addBackyardByPartnerId(Integer partnerId, BackyardDTO backyardDTO) throws BackyardWeddingException; //returns newly added backyardId.
  List<BackyardDTO> getBackyardsByPartnerId(Integer partnerId) throws BackyardWeddingException;
  String deleteBackyardById(Integer backyardId) throws BackyardWeddingException; //returns success message
}
