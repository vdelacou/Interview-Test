package com.mfg.repository;

import com.mfg.domain.Good;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * ^_^
 * Created by gaojiawei on 2016/9/30.
 */
public interface GoodRepository extends MongoRepository<Good, String> {

    Good findById(String id);

}
