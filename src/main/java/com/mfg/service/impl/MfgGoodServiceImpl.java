package com.mfg.service.impl;

import com.mfg.model.MfgGood;
import com.mfg.repository.MfgGoodRepository;
import com.mfg.service.MfgGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @PackageName com.mfg.service.impl
 * @Author Clark
 * @Date 2016/9/20
 * @Description
 */
@Service
public class MfgGoodServiceImpl implements MfgGoodService {
    @Autowired
    private MfgGoodRepository mfgGoodRepository;

    @Override
    public List<MfgGood> getGoods(Integer page, Integer size) {
        return mfgGoodRepository.getGoods(page, size);
    }

    @Override
    public Boolean saveGood(String name, Integer age) {
        MfgGood good = mfgGoodRepository.getGoodByName(name);
        if (good != null) {
            return false;
        } else {
            good = new MfgGood(name, age, new Date());
            mfgGoodRepository.saveGood(good);
        }
        return true;
    }

    @Override
    public Boolean updateGood(Long id, String name, Integer age) {
        MfgGood good = mfgGoodRepository.getGoodByName(name);
        if (good != null) {
            return false;
        } else {
            mfgGoodRepository.updateGood(id, name, age);
        }
        return true;
    }

    @Override
    public Boolean deleteGood(Long id) {
        MfgGood good = mfgGoodRepository.getGoodById(id);
        try {
            mfgGoodRepository.deleteGood(good);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public MfgGood getGoodById(Long id) {
        return mfgGoodRepository.getGoodById(id);
    }
}
