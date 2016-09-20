package com.mfg.repository.impl;

import com.mfg.model.MfgGood;
import com.mfg.repository.MfgGoodRepository;
import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @PackageName com.mfg.repository.impl
 * @Author Clark
 * @Date 2016/9/20
 * @Description
 */
@Repository
public class MfgGoodRepositoryImpl implements MfgGoodRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<MfgGood> getGoods(Integer page, Integer size) {
        Query query = new Query();
        query.skip((page - 1) * size);
        query.limit(size);
        return mongoTemplate.find(query, MfgGood.class);
    }

    @Override
    public void saveGood(MfgGood good) {
        mongoTemplate.save(good);
    }

    @Override
    public MfgGood getGoodByName(String name) {
        Query query = new Query();
        Criteria criteria = Criteria.where("name").is(name);
        query.addCriteria(criteria);
        return mongoTemplate.findOne(query, MfgGood.class);
    }

    @Override
    public void updateGood(Long id, String name, Integer age) {
        Query query = new Query();
        Criteria criteria = Criteria.where("id").is(id);
        query.addCriteria(criteria);
        Update update = new Update();
        if (name != null) {
            update.set("name", name);
        }
        if (age != null) {
            update.set("age", age);
        }
        mongoTemplate.updateFirst(query, update, MfgGood.class);
    }

    @Override
    public MfgGood getGoodById(Long id) {
        Query query = new Query();
        Criteria criteria = Criteria.where("id").is(id);
        query.addCriteria(criteria);
        return mongoTemplate.findOne(query, MfgGood.class);
    }

    @Override
    public void deleteGood(MfgGood good) {
        mongoTemplate.remove(good);
    }
}
