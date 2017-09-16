package com.mfg.Repository;

import com.mfg.domain.Good;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Created by hushuming on 2017/1/13.
 */


public interface GoodRepository extends MongoRepository<Good,String>{


    Optional<Good> findByName(String name);
    Optional<Good> findById(String id);
}
