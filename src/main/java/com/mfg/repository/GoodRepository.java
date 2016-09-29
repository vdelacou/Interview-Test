package com.mfg.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mfg.entities.Good;

public interface GoodRepository extends MongoRepository<Good, String> {

	Good findById(String id);
}
