package com.weddingapp.repository;

import com.weddingapp.entity.Partner;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends CrudRepository<Partner, String> {
  
}
