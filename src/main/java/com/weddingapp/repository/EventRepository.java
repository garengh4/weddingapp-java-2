package com.weddingapp.repository;

import com.weddingapp.entity.Event;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer>{
  
}
