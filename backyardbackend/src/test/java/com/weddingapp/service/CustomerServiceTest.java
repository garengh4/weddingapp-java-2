package com.weddingapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.weddingapp.dto.CustomerDTO;
import com.weddingapp.dto.EventDTO;
import com.weddingapp.entity.Customer;
import com.weddingapp.entity.Event;
import com.weddingapp.exception.BackyardWeddingException;
import com.weddingapp.repository.CustomerRepository;
import com.weddingapp.repository.EventRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

  @Mock
  private CustomerRepository customerRepository;

  @Mock
  private EventRepository eventRepository;

  @InjectMocks
  private CustomerService customerService = new CustomerServiceImpl();

  @Test
  public void registerNewCustomerValidTest() throws BackyardWeddingException {
    Customer customerInDB = new Customer();
    Mockito.when(customerRepository.save(Mockito.any())).thenReturn(customerInDB);

    CustomerDTO newCustomer = new CustomerDTO();
    newCustomer.setCustomerEmailId("clairebear@gmail.com");
    newCustomer.setFirstName("Claire");
    newCustomer.setLastName("Bear");

    String returned = customerService.registerNewCustomer(newCustomer);
    Assertions.assertEquals("clairebear@gmail.com", returned);
  }

  @Test
  public void getAllCustomerValidTest() throws BackyardWeddingException {
    List<Customer> listOfCustomersInDB = new ArrayList<>();
    Customer customerOne = new Customer();
    customerOne.setCustomerEmailId("deborahyue@gmail.com");
    customerOne.setFirstName("Deborah");
    customerOne.setLastName("Yue");
    Customer customerTwo = new Customer();
    customerTwo.setCustomerEmailId("clairebear@gmail.com");
    customerTwo.setFirstName("Claire");
    customerTwo.setLastName("Bear");
    listOfCustomersInDB.add(customerOne);
    listOfCustomersInDB.add(customerTwo);
    Mockito.when(customerRepository.findAll()).thenReturn(listOfCustomersInDB);

    List<CustomerDTO> returned = customerService.getAllCustomer();
    Assertions.assertEquals(2, returned.size());
  }

  @Test
  public void authenticateCustomerValidTest() throws BackyardWeddingException {

    Customer customer = new Customer();
    String emailIdFromDB = "deborahyue@gmail.com";
    String passwordFromDB = "Deborah@123";
    customer.setCustomerEmailId(emailIdFromDB);
    customer.setPassword(passwordFromDB);
    Optional<Customer> optionalCustomer = Optional.of(customer);

    String emailId = "deborahyue@gmail.com";
    String password = "Deborah@123";

    Mockito.when(customerRepository.findById(emailId.toLowerCase())).thenReturn(optionalCustomer);
    Assertions.assertEquals(emailIdFromDB,
        customerService.authenticateCustomer(emailId, password).getCustomerEmailId());

  }

  @Test
  public void deleteCustomerValidTest() throws BackyardWeddingException {
    Customer customerInDB = new Customer();
    customerInDB.setCustomerEmailId("deborahyue@gmail.com");
    customerInDB.setFirstName("Deborah");
    customerInDB.setLastName("Yue");
    Mockito.when(customerRepository.findById(Mockito.anyString())).thenReturn(Optional.of(customerInDB));

    String returned = customerService.deleteCustomer("deborahyue@gmail.com");
    Assertions.assertEquals("deborahyue@gmail.com", returned);
  }

  // ============================================================================================================================
  // Testing event functions

  @Test
  public void addEventToCustomerValidTest() throws BackyardWeddingException {

    // if successfully added, return added results
    EventDTO newEventDTO = new EventDTO();
    newEventDTO.setEventName("incoming new event");
    newEventDTO.setEventId(24);

    Event newEvent = new Event();
    newEvent.setEventId(newEventDTO.getEventId());
    newEvent.setEventName(newEventDTO.getEventName());
    List<Event> customerNewEvent = new ArrayList<>();
    customerNewEvent.add(newEvent);

    Customer addedEventCustomer = new Customer();
    addedEventCustomer.setCustomerEmailId("deborahyue@gmail.com");
    addedEventCustomer.setCustomerEvents(customerNewEvent);
    Mockito.when(customerRepository.save(Mockito.any())).thenReturn(addedEventCustomer);

    // setup no-event customer
    Customer noEventCustomer = new Customer();
    noEventCustomer.setCustomerEmailId("deborahyue@gmail.com");
    noEventCustomer.setCustomerEvents(new ArrayList<>());
    Mockito.when(customerRepository.findById(Mockito.anyString())).thenReturn(Optional.of(noEventCustomer));

    Integer newEventId = customerService.addEventToCustomer("deborahyue@gmail.com", newEventDTO);
    Assertions.assertEquals(24, newEventId);

  }

  @Test
  public void getEventByCustomerIdValidTest() throws BackyardWeddingException {

    List<Event> eventsInDB = new ArrayList<>();
    Event eventOne = new Event();
    eventOne.setEventId(1);
    Event eventTwo = new Event();
    eventTwo.setEventId(2);
    eventsInDB.add(eventOne);
    eventsInDB.add(eventTwo);

    Customer customerInDB = new Customer();
    customerInDB.setCustomerEmailId("deborahyue@gmail.com");
    customerInDB.setFirstName("resulting customer object upon customerRepository.findById");
    customerInDB.setCustomerEvents(eventsInDB);
    Mockito.when(customerRepository.findById(Mockito.anyString())).thenReturn(Optional.of(customerInDB));

    List<EventDTO> returned = customerService.getCustomerEvents("deborahyue@gmail.com");
    Assertions.assertEquals(2, returned.size());
  }

  @Test
  public void deleteCustomerEventValidTest() throws BackyardWeddingException {
    Customer customerInDB = new Customer();
    customerInDB.setCustomerEmailId("deborahyue@gmail.com");

    List<Event> eventsInDB = new ArrayList<>();
    Event eventOne = new Event();
    eventOne.setEventId(220);
    eventsInDB.add(eventOne);

    customerInDB.setCustomerEvents(eventsInDB);
    Mockito.when(customerRepository.findById(Mockito.anyString())).thenReturn(Optional.of(customerInDB));

    Integer deletedEventId = customerService.deleteCustomerEvent("deborahyue@gmail.com", 220);
    Assertions.assertEquals(220, deletedEventId);
  }

}