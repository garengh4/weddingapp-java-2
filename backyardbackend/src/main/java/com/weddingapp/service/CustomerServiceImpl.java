package com.weddingapp.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.weddingapp.dto.CustomerDTO;
import com.weddingapp.dto.EventDTO;
import com.weddingapp.entity.Customer;
import com.weddingapp.entity.Event;
import com.weddingapp.exception.BackyardWeddingException;
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

    // Linkedlist allows for constinat-time insertions/removal through iterators, but only sequentially.
    // you can walk the list forward and backward but finding index is proportional to size of list. O(n)

    // Adding/removing from head of LinkedList is O(1), but O(n) for ArrayList

    // Arraylist allows fast random read access. element can be read at constant time.
    // But adding/removing from anywhere but the end requires shifting all elements over

    // Arraylist is growable array. technically already at full capacity and creates another array with a 
    // size greater than previous

    // LinkledList is fast for adding/deleting element but slow to access element
    // ArrayList is foast for accessing specific element but can be slow to add/delete, especially in the middle.
    
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

    // if-condition needed for mockito
    if (customer.getCustomerEvents() != null && !customer.getCustomerEvents().isEmpty()) {
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
    }
    return customerDTO;
  }

  public String deleteCustomer(String customerEmailId) throws BackyardWeddingException {
    Customer customer = customerRepository.findById(customerEmailId)
        .orElseThrow(() -> new BackyardWeddingException("CustomerService.CUSTOMER_NOT_FOUND"));

    if (customer.getCustomerEvents() != null && !customer.getCustomerEvents().isEmpty()) {
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

    // Event newEventInDB = eventRepository.save(newEvent);
    listOfCustomerEvents.add(newEvent);
    customer.setCustomerEvents(listOfCustomerEvents);
    Customer customerAfterSave = customerRepository.save(customer);

    // get back last event after save (id should be auto_incremented)
    List<Event> newListOfCustomerEvents = customerAfterSave.getCustomerEvents();
    Event eventAfterSave = newListOfCustomerEvents.get(newListOfCustomerEvents.size() - 1);

    return eventAfterSave.getEventId();
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



}