package com.mfg.rest;

import com.mfg.domain.Good;
import com.mfg.domain.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * Created by Administrator on 2016/10/19.
 */
@RepositoryRestController
public class GoodController {
    @Autowired
    private GoodRepository repository;
    @RequestMapping(method = PUT, value = "/good")
    @ResponseBody
    public void updateGoods(@RequestBody Good good) {
        Good persistedGood = repository.findOne(good.getId());
        persistedGood.setAge(good.getAge());
        persistedGood.setName(good.getName());
        repository.save(persistedGood);
    }

}
