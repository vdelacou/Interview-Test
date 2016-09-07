package com.mfg.good.service;

import com.mfg.good.domain.Good;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author Sand Wen
 * @since 2016.9.7
 */
public interface GoodRepository extends MongoRepository<Good, String> {
}
