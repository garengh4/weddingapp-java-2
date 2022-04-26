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

  // @Test
  // public void getAllCustomerValidTest() throws BackyardWeddingException {
  //   List<Customer> listOfCustomersInDB = new ArrayList<>();
  //   Customer customerOne = new Customer();
  //   // customerOne.setCustomerId(1);
  //   customerOne.setFirstName("Deborah");
  //   customerOne.setLastName("Yue");
  //   Customer customerTwo = new Customer();
  //   // customerTwo.setCustomerId(2);
  //   customerTwo.setFirstName("Claire");
  //   customerTwo.setLastName("Bear");
  //   listOfCustomersInDB.add(customerOne);
  //   listOfCustomersInDB.add(customerTwo);
  //   Mockito.when(customerRepository.findAll()).thenReturn(listOfCustomersInDB);

  //   List<CustomerDTO> returned = customerService.getAllCustomer();
  //   Assertions.assertEquals(2, returned.size());
  // }

  // @Test
  // public void authenticateCustomerValidTest() throws BackyardWeddingException {
  //   Customer customerInDB = new Customer();
  //   // customerInDB.setCustomerId(98);
  //   customerInDB.setFirstName("Deborah");
  //   customerInDB.setLastName("Yue");
  //   Mockito.when(customerRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(customerInDB));

  //   Integer userId = 98;
  //   String firstName = "Deborah";
  //   String lastName = "Yue";

  //   CustomerDTO returned = customerService.authenticateCustomer(userId, firstName, lastName);
  //   Assertions.assertEquals(98, returned.getCustomerId());
  // }

  // @Test
  // public void deleteCustomerByIdValidTest() throws BackyardWeddingException {
  //   Customer customerInDB = new Customer();
  //   // customerInDB.setCustomerId(1);
  //   customerInDB.setFirstName("Deborah");
  //   customerInDB.setLastName("Yue");
  //   Mockito.when(customerRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(customerInDB));

  //   String returned = customerService.deleteCustomerById(1);
  //   Assertions.assertEquals("Service: customer deleted successfully.", returned);
  // }
  // // ============================================================================================================================
  // // Testing event functions
  
  // @Test
  // public void addEventByCustomerIdValidTest() throws BackyardWeddingException {
  //   Event returnedEventAfterSave = new Event();
  //   returnedEventAfterSave.setEventId(1);
  //   returnedEventAfterSave.setEventName("resulting event upon eventRepository.save");
  //   Mockito.when(eventRepository.save(Mockito.any())).thenReturn(returnedEventAfterSave);

  //   Customer customerInDB = new Customer();
  //   // customerInDB.setCustomerId(99);
  //   customerInDB.setFirstName("resulting customer upon customerRepository.findById");
  //   List<Event> listOfEvents = new ArrayList<>();
  //   listOfEvents.add(returnedEventAfterSave);
  //   customerInDB.setEvents(listOfEvents);
  //   Mockito.when(customerRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(customerInDB));

  //   EventDTO newEvent = new EventDTO();
  //   newEvent.setEventName("the joining of two houses");
  //   newEvent.setBackyardId(15);

  //   Integer returned = customerService.addEventByCustomerId(99, newEvent);
  //   Assertions.assertEquals(1, returned);
  // }

  // @Test
  // public void getEventByCustomerIdValidTest() throws BackyardWeddingException {
  //   List<Event> eventsInDB = new ArrayList<>();
  //   Event eventOne = new Event();
  //   eventOne.setEventId(1);
  //   Event eventTwo = new Event();
  //   eventTwo.setEventId(2);
  //   eventsInDB.add(eventOne);
  //   eventsInDB.add(eventTwo);
  //   Customer customerInDB = new Customer();
  //   // customerInDB.setCustomerId(1);
  //   customerInDB.setFirstName("resulting customer object upon customerRepository.findById");
  //   customerInDB.setEvents(eventsInDB);
  //   Mockito.when(customerRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(customerInDB));

  //   List<EventDTO> returned = customerService.getEventsByCustomerId(1);
  //   Assertions.assertEquals(2, returned.size());
  // }

  // @Test
  // public void deleteEventByIdValidTest() throws BackyardWeddingException{
  //   Event eventInDB = new Event();
  //   eventInDB.setEventId(22);
  //   Mockito.when(eventRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(eventInDB));

  //   String returned = customerService.deleteEventById(22);
  //   Assertions.assertEquals("SERVICE: event removed successfully.", returned);
  // }



}