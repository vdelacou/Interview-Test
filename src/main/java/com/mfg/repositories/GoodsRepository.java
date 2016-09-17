package com.mfg.repositories;

import com.mfg.domains.Good;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface GoodsRepository extends MongoRepository<Good,String>{

}
