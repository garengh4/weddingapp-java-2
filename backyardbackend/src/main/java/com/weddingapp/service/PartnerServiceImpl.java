// package com.weddingapp.service;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.stream.Collectors;

// import com.weddingapp.dto.BackyardDTO;
// import com.weddingapp.dto.PartnerDTO;
// import com.weddingapp.entity.Backyard;
// import com.weddingapp.entity.Partner;
// import com.weddingapp.exception.BackyardWeddingException;
// import com.weddingapp.repository.BackyardRepository;
// import com.weddingapp.repository.PartnerRepository;

// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// @Service
// @Transactional
// public class PartnerServiceImpl implements PartnerService {

//   @Autowired
//   private PartnerRepository partnerRepository;

//   @Autowired
//   private BackyardRepository backyardRepository;

//   @Override
//   public Integer registerNewPartner(PartnerDTO partnerDTO) throws BackyardWeddingException {
//     Partner partner = new Partner();
//     partner.setFirstName(partnerDTO.getFirstName());
//     partner.setLastName(partnerDTO.getLastName());

//     // not setting partnerRating here: who actually sets the partnerRating?
//     // not setting partnerBackyard here: the backyard will be added later.

//     Partner partnerFromDB = partnerRepository.save(partner);
//     return null;
//     // return partnerFromDB.getPartnerId();
//   }

//   @Override
//   public List<PartnerDTO> getAllPartner() throws BackyardWeddingException {
//     Iterable<Partner> partners = partnerRepository.findAll();

//     List<PartnerDTO> partnerDTOs = new ArrayList<>();
//     partners.forEach(entity -> {
//       PartnerDTO dto = new PartnerDTO();
//       // dto.setPartnerId(entity.getPartnerId());
//       dto.setFirstName(entity.getFirstName());
//       dto.setLastName(entity.getLastName());
//       // not setting partner backyard here?
//       // Probably combine getBackyardsByPartnerId to getAllPartner?
//       partnerDTOs.add(dto);
//     });
//     return partnerDTOs;
//   }

//   @Override
//   public PartnerDTO authenticatePartner(Integer partnerId, String firstName, String lastName)
//       throws BackyardWeddingException {
//     Partner partner = partnerRepository.findById(partnerId).orElseThrow(
//         () -> new BackyardWeddingException("SERVICE ERROR: Could not find partner with that partnerId."));

//     if (!firstName.equals(partner.getFirstName()) || !lastName.equals(partner.getLastName())) {
//       throw new BackyardWeddingException("SERVICE ERROR: incorrect first or last name");
//     }

//     PartnerDTO partnerDTO = new PartnerDTO();
//     // partnerDTO.setPartnerId(partner.getPartnerId());
//     partnerDTO.setFirstName(partner.getFirstName());
//     partnerDTO.setLastName(partner.getLastName());

//     List<Backyard> partnerBackyards = partner.getBackyards();
//     List<BackyardDTO> partnerBackyardsDTO = partnerBackyards.stream().map(entity -> {
//       BackyardDTO dto = new BackyardDTO();
//       dto.setBackyardId(entity.getBackyardId());
//       dto.setBackyardDescription(entity.getBackyardDescription());
//       dto.setBackyardCity(entity.getBackyardCity());
//       dto.setBackyardCost(entity.getBackyardCost());
//       // not setting partnerId here because it seems redundant.
//       return dto;
//     }).collect(Collectors.toList());

//     partnerDTO.setPartnerBackyards(partnerBackyardsDTO);
//     return partnerDTO;
//   }

//   @Override
//   public String deletePartnerById(Integer partnerId) throws BackyardWeddingException {
//     Partner partner = partnerRepository.findById(partnerId).orElseThrow(
//         () -> new BackyardWeddingException("SERVICE ERROR: Could not find partner with that partnerId."));

//     partnerRepository.delete(partner);
//     return "SERVICE: partner deleted successfully.";
//   }
//   // ==================================================================================================================

//   @Override
//   public Integer addBackyardByPartnerId(Integer partnerId, BackyardDTO backyardDTO) throws BackyardWeddingException {
//     Partner partner = partnerRepository.findById(partnerId).orElseThrow(
//         () -> new BackyardWeddingException("SERVICE ERROR: Could not find partner with that partnerId."));

//     List<Backyard> listOfPartnerBackyards = partner.getBackyards();

//     Backyard newBackyard = new Backyard();
//     // newBackyard.setPartnerId(partnerId);
//     newBackyard.setBackyardDescription(backyardDTO.getBackyardDescription());
//     newBackyard.setBackyardCity(backyardDTO.getBackyardCity());
//     newBackyard.setBackyardCost(backyardDTO.getBackyardCost());

//     Backyard newBackyardInDB = backyardRepository.save(newBackyard);
//     listOfPartnerBackyards.add(newBackyard);
//     partner.setBackyards(listOfPartnerBackyards);
//     partnerRepository.save(partner);

//     return newBackyardInDB.getBackyardId();
//   }

//   @Override
//   public List<BackyardDTO> getBackyardsByPartnerId(Integer partnerId) throws BackyardWeddingException {
//     Partner partner = partnerRepository.findById(partnerId).orElseThrow(
//         () -> new BackyardWeddingException("SERVICE ERROR: Could not find partner with that partnerId."));

//     List<Backyard> backyards = partner.getBackyards();

//     List<BackyardDTO> listBackyards = new ArrayList<>();
//     backyards.forEach(partnerBackyard -> {
//       BackyardDTO backyard = new BackyardDTO();
//       backyard.setBackyardId(partnerBackyard.getBackyardId());
//       backyard.setBackyardDescription(partnerBackyard.getBackyardDescription());
//       backyard.setBackyardCity(partnerBackyard.getBackyardCity());
//       backyard.setBackyardCost(partnerBackyard.getBackyardCost());
//       backyard.setPartnerId(partnerId);
//       listBackyards.add(backyard);
//     });
//     return listBackyards;
//   }

//   @Override
//   public String deleteBackyardById(Integer backyardId) throws BackyardWeddingException {
//     Backyard backyard = backyardRepository.findById(backyardId).orElseThrow(
//         () -> new BackyardWeddingException("SERVICE ERROR: Could not find backyard with that backyardId."));

//     backyardRepository.delete(backyard);
//     return "SERVICE: backyard removed successfully.";
//   }
// }