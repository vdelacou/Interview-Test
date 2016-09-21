package com.mfg.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.mfg.model.Good;

public interface GoodMongoRepository extends MongoRepository<Good, String> {

	List<Good> findByName(@Param("name") String name);
	Long countByName(@Param("name") String name);

}