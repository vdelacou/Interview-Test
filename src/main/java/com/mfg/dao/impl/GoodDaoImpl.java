package com.mfg.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mfg.dao.GoodDao;
import com.mfg.domain.Good;

@Repository("goodDao")
public class GoodDaoImpl implements GoodDao{
	@Resource
	private MongoTemplate mongoTemplate;

	@Override
	public List<Good> queryList(int skip, int limit) {
		Query query = new Query();
	    query.skip(skip).limit(limit);
		return this.mongoTemplate.find(query, Good.class);
	}

	@Override
	public void insertGood(Good good) {
		this.mongoTemplate.insert(good);
		
	}

	@Override
	public void updateGood(Good good) {
		Query query = new Query();
		query.addCriteria(new Criteria("id").is(good.getId()));
		Update update = new Update();
		update.set("name", good.getName());
		update.set("age", good.getAge());
		update.set("productionDate", good.getProductionDate());
		this.mongoTemplate.updateFirst(query, update, Good.class); 
	}

	@Override
	public void deleteGoodById(String id) {
		Query query = new Query();
		query.addCriteria(new Criteria("id").is(id));
		this.mongoTemplate.remove(query, Good.class);
	}

	@Override
	public Good getGoodById(String id) {
		Query query = new Query();
		query.addCriteria(new Criteria("id").is(id));
		return this.mongoTemplate.findOne(query, Good.class);
	}
	
}
