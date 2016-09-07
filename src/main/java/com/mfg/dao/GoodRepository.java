package com.mfg.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.mfg.entity.Good;

/**
 * 
 * @author duand
 *
 */
public interface GoodRepository extends MongoRepository<Good, String> {
	Good findByName(String name);
}
