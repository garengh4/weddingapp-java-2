package com.weddingapp.repository;

import com.weddingapp.entity.Backyard;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackyardRepository extends CrudRepository<Backyard, Integer>{
  
}
