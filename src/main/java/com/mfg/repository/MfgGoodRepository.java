package com.mfg.repository;

import com.mfg.model.MfgGood;

import java.util.List;

/**
 * @PackageName com.mfg.repository
 * @Author Clark
 * @Date 2016/9/20
 * @Description
 */
public interface MfgGoodRepository {
    List<MfgGood> getGoods(Integer page, Integer size);

    void saveGood(MfgGood good);

    MfgGood getGoodByName(String name);

    void updateGood(Long id, String name, Integer age);

    MfgGood getGoodById(Long id);

    void deleteGood(MfgGood good);
}
