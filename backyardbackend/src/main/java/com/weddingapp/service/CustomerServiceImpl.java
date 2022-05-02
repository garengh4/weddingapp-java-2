package com.weddingapp.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.weddingapp.dto.BackyardDTO;
import com.weddingapp.dto.CustomerDTO;
import com.weddingapp.dto.EventDTO;
import com.weddingapp.entity.Backyard;
import com.weddingapp.entity.Customer;
import com.weddingapp.entity.Event;
import com.weddingapp.exception.BackyardWeddingException;
import com.weddingapp.repository.BackyardRepository;
import com.weddingapp.repository.CustomerRepository;
import com.weddingapp.repository.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private BackyardRepository backyardRepository;

  @Override
  public String registerNewCustomer(CustomerDTO customerDTO) throws BackyardWeddingException {
    boolean isEmailAvailable = customerRepository.findById(customerDTO.getCustomerEmailId().toLowerCase()).isEmpty();

    if (isEmailAvailable) {
      Customer newCustomer = new Customer();
      newCustomer.setCustomerEmailId(customerDTO.getCustomerEmailId());
      newCustomer.setFirstName(customerDTO.getFirstName());
      newCustomer.setLastName(customerDTO.getLastName());
      newCustomer.setPassword(customerDTO.getPassword());
      customerRepository.save(newCustomer);
    } else {
      throw new BackyardWeddingException("CustomerService.EMAIL_ID_ALREADY_IN_USE");
    }
    return customerDTO.getCustomerEmailId();
  }

  @Override
  public List<CustomerDTO> getAllCustomer() throws BackyardWeddingException {
    Iterable<Customer> customers = customerRepository.findAll();

    // convert customers to customerDTOs
    List<CustomerDTO> customerDTOs = new LinkedList<CustomerDTO>();
    for (Customer c : customers) {
      CustomerDTO dto = new CustomerDTO();
      dto.setCustomerEmailId(c.getCustomerEmailId());
      dto.setFirstName(c.getFirstName());
      dto.setLastName(c.getLastName());
      dto.setPassword(c.getPassword());
      // Not setting events here. (?)
      customerDTOs.add(dto);
    }
    return customerDTOs;
  }

  @Override
  public CustomerDTO authenticateCustomer(String customerEmailId, String password) throws BackyardWeddingException {
    Customer customer = customerRepository.findById(customerEmailId)
        .orElseThrow(() -> new BackyardWeddingException("CustomerService.CUSTOMER_NOT_FOUND"));

    if (!password.equals(customer.getPassword())) {
      throw new BackyardWeddingException("CustomerService.INVALID_CREDENTIALS");
    }

    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setCustomerEmailId(customer.getCustomerEmailId());
    customerDTO.setFirstName(customer.getFirstName());
    customerDTO.setLastName(customer.getLastName());
    customerDTO.setPassword(customer.getPassword());

    // customerEvent to customerEventDTO
    List<EventDTO> customerEventsDTO = customer.getCustomerEvents().stream().map(entity -> {
      EventDTO eventDTO = new EventDTO();
      eventDTO.setEventId(entity.getEventId());
      eventDTO.setEventName(entity.getEventName());
      eventDTO.setEventDescription(entity.getEventName());
      eventDTO.setEventDate(entity.getEventDate());
      return eventDTO;
    }).collect(Collectors.toList());

    customerDTO.setCustomerEvents(customerEventsDTO);
    return customerDTO;

  }

  public String deleteCustomer(String customerEmailId) throws BackyardWeddingException {
    Customer customer = customerRepository.findById(customerEmailId)
        .orElseThrow(() -> new BackyardWeddingException("CustomerService.CUSTOMER_NOT_FOUND"));

    if (!customer.getCustomerEvents().isEmpty()) {
      throw new BackyardWeddingException("CustomerService.DELETE_CUSTOMER_INVALID");
    }

    customerRepository.delete(customer);
    return customerEmailId;

  }

  // ==================================================================================================================
  @Override
  public Integer addEventToCustomer(String customerEmailId, EventDTO eventDTO) throws BackyardWeddingException {
    Customer customer = customerRepository.findById(customerEmailId)
        .orElseThrow(() -> new BackyardWeddingException("CustomerService.CUSTOMER_NOT_FOUND"));

    List<Event> listOfCustomerEvents = customer.getCustomerEvents();

    Event newEvent = new Event();
    newEvent.setCustomerEmailId(customerEmailId);
    newEvent.setEventName(eventDTO.getEventName());
    newEvent.setEventDescription(eventDTO.getEventDescription());
    newEvent.setEventDate(eventDTO.getEventDate());
    newEvent.setBackyardId(eventDTO.getBackyardId());

    Event newEventInDB = eventRepository.save(newEvent);
    listOfCustomerEvents.add(newEvent);
    customer.setCustomerEvents(listOfCustomerEvents);
    customerRepository.save(customer);

    return newEventInDB.getEventId();
  }

  @Override
  public List<EventDTO> getCustomerEvents(String customerEmailId) throws BackyardWeddingException {
    Customer customer = customerRepository.findById(customerEmailId)
        .orElseThrow(() -> new BackyardWeddingException("CustomerService.CUSTOMER_NOT_FOUND"));

    List<Event> events = customer.getCustomerEvents();

    // convert events to eventsDTO
    List<EventDTO> eventsDTO = new LinkedList<EventDTO>();
    for (Event event : events) {
      EventDTO dto = new EventDTO();
      dto.setEventId(event.getEventId());
      dto.setEventName(event.getEventName());
      dto.setEventDescription(event.getEventDescription());
      dto.setEventDate(event.getEventDate());
      dto.setCustomerEmailId(customerEmailId);
      dto.setBackyardId(event.getBackyardId());
      eventsDTO.add(dto);
    }
    return eventsDTO;

  }

  @Override
  public Integer deleteCustomerEvent(String customerEmailId, Integer eventId) throws BackyardWeddingException {
    Customer customer = customerRepository.findById(customerEmailId)
        .orElseThrow(() -> new BackyardWeddingException("CustomerService.CUSTOMER_NOT_FOUND"));
    List<Event> events = customer.getCustomerEvents();

    for (Event currentEvent : events) {
      if (currentEvent.getEventId().equals(eventId)) {
        events.remove(currentEvent);
        customer.setCustomerEvents(events);
        eventRepository.delete(currentEvent);
        break;
      } else {
        throw new BackyardWeddingException("CustomerService.EVENT_NOT_FOUND");
      }
    }
    return eventId;
  }

  // ============================================================================================================================

  @Override
  public List<BackyardDTO> getAllBackyards() throws BackyardWeddingException {
    List<Backyard> backyardEntityList = (List<Backyard>) backyardRepository.findAll();
    if (backyardEntityList.isEmpty()) {
      throw new BackyardWeddingException("No backyards found.");
    }

    List<BackyardDTO> dtoList = new LinkedList<BackyardDTO>();
    backyardEntityList.forEach(entity -> {
      BackyardDTO dto = new BackyardDTO();
      dto.setBackyardCity(entity.getBackyardCity());
      dto.setBackyardCost(entity.getBackyardCost());
      dto.setBackyardDescription(entity.getBackyardDescription());
      dto.setBackyardId(entity.getBackyardId());
      // dto.setPartnerId(entity.getPartnerId());
      dtoList.add(dto);
    });
    return dtoList;
  }

  @Override
  public CustomerDTO getCustomerById(Integer customerId) throws BackyardWeddingException {
    // Customer customer = customerRepository.findById(customerId).orElseThrow(
    // () -> new BackyardWeddingException("SERVICE ERROR: Could not find customer
    // with that customerId."));

    // CustomerDTO customerDTO = new CustomerDTO();
    // // customerDTO.setCustomerId(customer.getCustomerId());
    // customerDTO.setFirstName(customer.getFirstName());
    // customerDTO.setLastName(customer.getLastName());

    // List<Event> customerEvents = customer.getEvents();
    // // copy list entity to list dto
    // List<EventDTO> customerEventsDTO = customerEvents.stream().map(entity -> {
    // EventDTO dto = new EventDTO();
    // dto.setEventId(entity.getEventId());
    // dto.setEventName(entity.getEventName());
    // dto.setEventDate(entity.getEventDate());
    // dto.setBackyardId(entity.getBackyardId());
    // return dto;
    // }).collect(Collectors.toList());

    // customerDTO.setCustomerEvents(customerEventsDTO);

    // return customerDTO;
    return null;

  }

  @Override
  public EventDTO updateEvent(EventDTO eventDto) throws BackyardWeddingException {
    Event event = eventRepository.findById(eventDto.getEventId())
        .orElseThrow(() -> new BackyardWeddingException("Event not found."));

    event.setBackyardId(eventDto.getBackyardId());
    // event.setCustomerId(eventDto.getCustomerId());
    event.setEventDate(eventDto.getEventDate());
    event.setEventId(eventDto.getEventId());
    event.setEventName(eventDto.getEventName());

    eventRepository.save(event);
    return eventDto;

  }

}