/**
 * Author: Dustin Mao
 */
package com.mfg;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GoodRepository extends MongoRepository<Good, Long> {
    Good findById(long id);
}
