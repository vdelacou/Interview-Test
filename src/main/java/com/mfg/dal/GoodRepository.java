package com.mfg.dal;

import com.mfg.model.Good;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author jeffrey on 9/6/16.
 * @project interview
 */
public interface GoodRepository extends MongoRepository<Good, Integer> {
}
