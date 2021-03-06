package com.weddingapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.weddingapp.dto.BackyardDTO;
import com.weddingapp.dto.PartnerDTO;
import com.weddingapp.entity.Backyard;
import com.weddingapp.entity.Partner;
import com.weddingapp.exception.BackyardWeddingException;
import com.weddingapp.repository.BackyardRepository;
import com.weddingapp.repository.PartnerRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PartnerServiceImpl implements PartnerService {

  @Autowired
  private PartnerRepository partnerRepository;

  @Autowired
  private BackyardRepository backyardRepository;

  @Override
  public String registerNewPartner(PartnerDTO partnerDTO) throws BackyardWeddingException {
    boolean isEmailAvailable = partnerRepository.findById(partnerDTO.getPartnerEmailId().toLowerCase()).isEmpty();

    if (isEmailAvailable) {
      Partner newPartner = new Partner();
      newPartner.setPartnerEmailId(partnerDTO.getPartnerEmailId());
      newPartner.setFirstName(partnerDTO.getFirstName());
      newPartner.setLastName(partnerDTO.getLastName());
      newPartner.setPassword(partnerDTO.getPassword());
      partnerRepository.save(newPartner);
    } else {
      throw new BackyardWeddingException("PartnerService.EMAIL_ID_ALREADY_IN_USE");
    }
    return partnerDTO.getPartnerEmailId();
  }

  @Override
  public List<PartnerDTO> getAllPartner() throws BackyardWeddingException {
    Iterable<Partner> partners = partnerRepository.findAll();
    List<PartnerDTO> partnerDTOs = new ArrayList<>();
    for (Partner current : partners) {
      PartnerDTO dto = new PartnerDTO();
      dto.setPartnerEmailId(current.getPartnerEmailId());
      dto.setFirstName(current.getFirstName());
      dto.setLastName(current.getLastName());
      dto.setPassword(current.getPassword());
      partnerDTOs.add(dto);
    }
    return partnerDTOs;
  }

  @Override
  public PartnerDTO authenticatePartner(String emailId, String password) throws BackyardWeddingException {
    Partner partner = partnerRepository.findById(emailId)
        .orElseThrow(() -> new BackyardWeddingException("PartnerService.PARTNER_NOT_FOUND"));

    if (!password.equals(partner.getPassword())) {
      throw new BackyardWeddingException("PartnerService.INVALID_CREDENTIALS");
    }

    PartnerDTO partnerDTO = new PartnerDTO();
    partnerDTO.setPartnerEmailId(emailId);
    partnerDTO.setFirstName(partner.getFirstName());
    partnerDTO.setLastName(partner.getLastName());
    partnerDTO.setPassword(password);

    if (partner.getPartnerBackyards() != null && !partner.getPartnerBackyards().isEmpty()) {
      List<BackyardDTO> backyardDTOs = partner.getPartnerBackyards().stream().map(e -> {
        BackyardDTO backyardDTO = new BackyardDTO();
        backyardDTO.setBackyardId(e.getBackyardId());
        backyardDTO.setBackyardName(e.getBackyardName());
        backyardDTO.setBackyardDescription(e.getBackyardDescription());
        backyardDTO.setBackyardCity(e.getBackyardCity());
        backyardDTO.setBackyardCost(e.getBackyardCost());
        backyardDTO.setPartnerEmailId(emailId);
        return backyardDTO;
      }).collect(Collectors.toList());
      partnerDTO.setPartnerBackyards(backyardDTOs);
    }
    return partnerDTO;

  }

  @Override
  public String deletePartner(String partnerEmailId) throws BackyardWeddingException {
    Partner partner = partnerRepository.findById(partnerEmailId)
        .orElseThrow(() -> new BackyardWeddingException("PartnerService.PARTNER_NOT_FOUND"));

    if (partner.getPartnerBackyards() != null && !partner.getPartnerBackyards().isEmpty()) {
      throw new BackyardWeddingException("PartnerService.DELETE_PARTNER_INVALID");
    }
    partnerRepository.delete(partner);
    return partnerEmailId;
  }

  // ==================================================================================================================

  @Override
  public Integer addBackyardToPartner(String partnerEmailId, BackyardDTO backyardDTO) throws BackyardWeddingException {

    // validation for if partner exists in db
    partnerRepository.findById(partnerEmailId)
        .orElseThrow(() -> new BackyardWeddingException("PartnerService.PARTNER_NOT_FOUND"));

    Backyard backyard = new Backyard();
    backyard.setPartnerEmailId(partnerEmailId);
    backyard.setBackyardName(backyardDTO.getBackyardName());
    backyard.setBackyardDescription(backyardDTO.getBackyardDescription());
    backyard.setBackyardCity(backyardDTO.getBackyardCity());
    backyard.setBackyardCost(backyardDTO.getBackyardCost());

    Backyard returned = backyardRepository.save(backyard);
    return returned.getBackyardId();
  }

  @Override
  public List<BackyardDTO> getPartnerBackyards(String partnerEmailId) throws BackyardWeddingException {
    Partner partner = partnerRepository.findById(partnerEmailId)
        .orElseThrow(() -> new BackyardWeddingException("PartnerService.PARTNER_NOT_FOUND"));

    List<BackyardDTO> backyardDTOs = partner.getPartnerBackyards().stream().map(e -> {
      BackyardDTO backyardDTO = new BackyardDTO();
      backyardDTO.setBackyardId(e.getBackyardId());
      backyardDTO.setBackyardName(e.getBackyardName());
      backyardDTO.setBackyardDescription(e.getBackyardDescription());
      backyardDTO.setBackyardCity(e.getBackyardCity());
      backyardDTO.setBackyardCost(e.getBackyardCost());
      backyardDTO.setPartnerEmailId(partnerEmailId);
      return backyardDTO;
    }).collect(Collectors.toList());

    return backyardDTOs;
  }

  @Override
  public Integer deletePartnerBackyard(String partnerEmailId, Integer backyardId) throws BackyardWeddingException {
    Partner partner = partnerRepository.findById(partnerEmailId)
        .orElseThrow(() -> new BackyardWeddingException("PartnerService.PARTNER_NOT_FOUND"));

    List<Backyard> backyards = partner.getPartnerBackyards();

    Backyard backyardToRemove = null;
    for (Backyard current : backyards) {
      if (current.getBackyardId().equals(backyardId)) {
        backyardToRemove = current;
        break;
      }
    }

    backyards.remove(backyardToRemove);
    partner.setPartnerBackyards(backyards);

    if (backyardToRemove == null) {
      throw new BackyardWeddingException("PartnerService.BACKYARD_NOT_FOUND");
    }

    return backyardToRemove.getBackyardId();
  }

  @Override
  public List<BackyardDTO> getAllBackyards() throws BackyardWeddingException {
    Iterable<Backyard> backyards = backyardRepository.findAll();

    List<BackyardDTO> result = new ArrayList<>();
    for(Backyard current: backyards) {
      BackyardDTO dto = new BackyardDTO();
      dto.setBackyardId(current.getBackyardId());
      dto.setBackyardName(current.getBackyardName());
      dto.setBackyardCity(current.getBackyardCity());
      dto.setBackyardDescription(current.getBackyardDescription());
      dto.setBackyardCost(current.getBackyardCost());
      dto.setPartnerEmailId(current.getPartnerEmailId());
      result.add(dto);
    }

    return result;
  }

}