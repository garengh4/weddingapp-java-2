package com.weddingapp.api;

import java.util.List;

import com.weddingapp.dto.BackyardDTO;
import com.weddingapp.dto.CustomerDTO;
import com.weddingapp.dto.EventDTO;
import com.weddingapp.exception.BackyardWeddingException;
import com.weddingapp.service.CustomerService;

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
@RequestMapping(value = "/customer-api")
public class CustomerAPI {

  @Autowired
  Environment environment;

  @Autowired
  CustomerService customerService;

  @PostMapping(value = "/register")
  public ResponseEntity<String> registerNewCustomer(@RequestBody CustomerDTO customerDTO)
      throws BackyardWeddingException {
    String registeredWithEmailId = customerService.registerNewCustomer(customerDTO);
    registeredWithEmailId = environment.getProperty("CustomerAPI.CUSTOMER_REGISTRATION_SUCCESS")
        + registeredWithEmailId;
    return new ResponseEntity<>(registeredWithEmailId, HttpStatus.CREATED);
  }

  @GetMapping(value = "/getall")
  public ResponseEntity<List<CustomerDTO>> getAllCustomers() throws BackyardWeddingException {
    List<CustomerDTO> response = customerService.getAllCustomer();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping(value = "/authenticate")
  public ResponseEntity<CustomerDTO> authenticateCustomer(@RequestBody CustomerDTO customerDTO) throws BackyardWeddingException {
    CustomerDTO dto = customerService.authenticateCustomer(customerDTO.getCustomerEmailId(), customerDTO.getPassword());
    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  // @DeleteMapping(value = "/delete/{customerId}")
  // public ResponseEntity<String> deleteCustomerById(@PathVariable(name =
  // "customerId") Integer customerId)
  // throws BackyardWeddingException {
  // String successMsg = customerService.deleteCustomerById(customerId);
  // return new ResponseEntity<>(successMsg, HttpStatus.OK);
  // }

  // @PostMapping(value = "/addevent/{customerId}")
  // public ResponseEntity<String> addEventByCustomerId(@PathVariable(name =
  // "customerId") Integer customerId,
  // @RequestBody EventDTO eventDTO) throws BackyardWeddingException {
  // Integer newEventId = customerService.addEventByCustomerId(customerId,
  // eventDTO);
  // String successMsg = "New event added with new eventId: " + newEventId;
  // return new ResponseEntity<>(successMsg, HttpStatus.CREATED);
  // }

  // @GetMapping(value = "/getallevents/{customerId}")
  // public ResponseEntity<List<EventDTO>>
  // getEventsByCustomerId(@PathVariable("customerId") Integer customerId)
  // throws BackyardWeddingException {
  // List<EventDTO> eventDtoList =
  // customerService.getEventsByCustomerId(customerId);
  // return new ResponseEntity<>(eventDtoList, HttpStatus.OK);
  // }

  // @DeleteMapping(value = "/deleteevent/{eventId}")
  // public ResponseEntity<String> deleteEventById(
  // @PathVariable("eventId") Integer eventId) throws BackyardWeddingException {
  // String successMsg = customerService.deleteEventById(eventId);
  // return new ResponseEntity<>(successMsg, HttpStatus.OK);
  // }

  // @GetMapping(value = "/getallbackyards")
  // public ResponseEntity<List<BackyardDTO>> getAllBackyards() throws
  // BackyardWeddingException {
  // List<BackyardDTO> backyards = customerService.getAllBackyards();
  // return new ResponseEntity<>(backyards, HttpStatus.OK);
  // }

  // //
  // =============================================================================================================================================

  // @GetMapping(value = "/getcustomer")
  // public ResponseEntity<CustomerDTO> getCustomerWithId(@RequestBody CustomerDTO
  // customerDTO)
  // throws BackyardWeddingException {
  // CustomerDTO returned =
  // customerService.getCustomerById(customerDTO.getCustomerId());
  // return new ResponseEntity<CustomerDTO>(returned, HttpStatus.OK);
  // }

  // @PostMapping(value = "/updateevent")
  // public ResponseEntity<EventDTO> updateEvent(@RequestBody EventDTO eventDto)
  // throws BackyardWeddingException {
  // EventDTO dto = customerService.updateEvent(eventDto);
  // return new ResponseEntity<>(dto, HttpStatus.OK);
  // }

}
