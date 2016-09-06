package com.mfg.service;

import com.google.common.collect.Lists;
import com.mfg.dal.GoodRepository;
import com.mfg.model.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jeffrey on 9/6/16.
 * @project interview
 */
@Service("goodService")
public class GoodService {
    @Autowired
    private GoodRepository goodRepository;

    public Good getGood(int id) {
        return goodRepository.findOne(id);
    }

    public List<Good> getGoods(int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        Page<Good> goodPage = goodRepository.findAll(pageable);

        return Lists.newArrayList(goodPage);
    }

    public void addGood(Good good) {
        goodRepository.save(good);
    }

    public void updateGood(Good good) {
        goodRepository.save(good);
    }

    public void deleteGood(int id) {
        goodRepository.delete(id);
    }
}
