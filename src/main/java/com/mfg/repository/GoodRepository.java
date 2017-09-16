package com.mfg.repository;

import com.mfg.po.Good;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lw on 16/12/7.
 */
public interface GoodRepository extends MongoRepository<Good, String> {
    @Query(value = "{'name':?0}")
    Good findByName(String name);
}