package com.mfg.good.service;

import com.mfg.good.domain.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sand Wen
 * @since 2016.9.7
 */
@Service
public class GoodService {
    @Autowired
    private GoodRepository repo;

    public List<Good> getGoodList(int page, int size){
        Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.DESC, "productionDate"));

        return repo.findAll(pageable).getContent();
    }

    public Good getGood(String id){
        return repo.findOne(id);
    }

    public String createGood(Good good){
        repo.save(good);

        return good.getId();
    }

    public void saveGood(Good good){
        repo.save(good);
    }

    public void deleteGood(String id){
        repo.delete(id);
    }
}
