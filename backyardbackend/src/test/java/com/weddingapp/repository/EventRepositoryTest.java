package com.weddingapp.repository;

import java.util.Optional;

import com.weddingapp.entity.Event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class EventRepositoryTest {

  @Autowired
  private EventRepository eventRepository;

  private Event event;
  
  @BeforeEach
  public void setUp() {
    event = new Event();
    event.setEventName("walking the cat");
  }

  @Test
  public void saveEventValidTest() {
    Event eventFromDB = eventRepository.save(event);
    // Assertions.assertEquals(1, eventFromDB.getEventId());
    Assertions.assertEquals("walking the cat", eventFromDB.getEventName());
  }

  @Test
  public void findByIdValidTest() {
    eventRepository.save(event);
    Optional<Event> eventContainer = eventRepository.findById(1);
    Assertions.assertTrue(eventContainer.isPresent());
  }

  @Test
  public void findByIdInvalidTest() {
    Optional<Event> eventContainer = eventRepository.findById(1);
    Assertions.assertTrue(eventContainer.isEmpty());
  }

}