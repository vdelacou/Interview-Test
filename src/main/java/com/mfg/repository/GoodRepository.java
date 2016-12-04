package com.mfg.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mfg.model.Good;

@Repository
public class GoodRepository {

  @Autowired
  private MongoTemplate mongoTemplate;

  public <T> void saveOrUpdate(T t) {
    mongoTemplate.save(t);
  }

  public <T> void batchSaveOrUpdate(List<T> t) {
    for (T t2 : t) {
      mongoTemplate.save(t2);
    }
  }

  public List<Good> findGoodsByPage(int page, int size) {
    return mongoTemplate.find(
        new Query().with(new Sort(Direction.DESC, "productionDate")).skip(page * size).limit(size),
        Good.class);
  }

  public Good findGoodById(String id) {
    return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), Good.class);
  }

  public boolean delGoodById(String id) {

    return mongoTemplate.remove(new Query(Criteria.where("id").is(id)), Good.class).getN() == 0
        ? false
        : true;

  }
}
