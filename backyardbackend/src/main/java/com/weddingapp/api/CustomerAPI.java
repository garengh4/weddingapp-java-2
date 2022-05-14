package com.weddingapp.api;

import java.util.List;

import javax.validation.constraints.Pattern;

import com.weddingapp.dto.CustomerDTO;
import com.weddingapp.dto.EventDTO;
import com.weddingapp.exception.BackyardWeddingException;
import com.weddingapp.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping(value = "/customer-api")
@Validated
public class CustomerAPI {

  @Autowired
  Environment environment;

  @Autowired
  CustomerService customerService;

  @PostMapping(value = "/customer/register")
  public ResponseEntity<String> registerNewCustomer(@RequestBody CustomerDTO customerDTO)
      throws BackyardWeddingException {
    String registeredWithEmailId = customerService.registerNewCustomer(customerDTO);
    registeredWithEmailId = environment.getProperty("CustomerAPI.REGISTRATION_SUCCESS")
        + registeredWithEmailId;
    return new ResponseEntity<>(registeredWithEmailId, HttpStatus.CREATED);
  }

  @GetMapping(value = "/customer/getall")
  public ResponseEntity<List<CustomerDTO>> getAllCustomers() throws BackyardWeddingException {
    List<CustomerDTO> response = customerService.getAllCustomer();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping(value = "/customer/authenticate")
  public ResponseEntity<CustomerDTO> authenticateCustomer(@RequestBody CustomerDTO customerDTO)
      throws BackyardWeddingException {
    CustomerDTO dto = customerService.authenticateCustomer(customerDTO.getCustomerEmailId(), customerDTO.getPassword());
    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  @DeleteMapping(value = "/customer/{customerEmailId:.+}/delete")
  public ResponseEntity<String> deleteCustomer(
      @Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+", message = "{invalid.email.format}") @PathVariable(name = "customerEmailId") String customerEmailId)
      throws BackyardWeddingException {
    String deleteWithEmailId = customerService.deleteCustomer(customerEmailId);
    deleteWithEmailId = environment.getProperty("CustomerAPI.DELETE_CUSTOMER_SUCCESS") + deleteWithEmailId;
    return new ResponseEntity<>(deleteWithEmailId, HttpStatus.OK);
  }

  // ==================================================================================================================
  @PostMapping(value = "/event/{customerEmailId:.+}/add")
  public ResponseEntity<String> addEventToCustomer(
      @Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+", message = "{invalid.email.format}") @PathVariable(name = "customerEmailId") String customerEmailId,
      @RequestBody EventDTO eventDTO) throws BackyardWeddingException {

    Integer newEventId = customerService.addEventToCustomer(customerEmailId, eventDTO);
    String successMsg = environment.getProperty("CustomerAPI.ADD_EVENT_SUCCESS") + newEventId;
    return new ResponseEntity<>(successMsg, HttpStatus.CREATED);
  }

  @GetMapping(value = "/event/{customerEmailId:.+}/getall")
  public ResponseEntity<List<EventDTO>> getCustomerEvents(
      @Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+", message = "{invalid.email.format}") @PathVariable(name = "customerEmailId") String customerEmailId)
      throws BackyardWeddingException {
    List<EventDTO> eventsDTO = customerService.getCustomerEvents(customerEmailId);
    return new ResponseEntity<>(eventsDTO, HttpStatus.OK);
  }

  @DeleteMapping(value = "/event/{customerEmailId:.+}/delete/{eventId}")
  public ResponseEntity<String> deleteCustomerEvent(
      @Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+", message = "{invalid.email.format}") @PathVariable(name = "customerEmailId") String customerEmailId,
      @PathVariable(name = "eventId")Integer eventId) throws BackyardWeddingException {

    Integer deletedEventId = customerService.deleteCustomerEvent(customerEmailId, eventId);
    String successMsg = environment.getProperty("CustomerAPI.DELETE_EVENT_SUCCESS") + deletedEventId;
    return new ResponseEntity<>(successMsg, HttpStatus.OK);
  }

}
