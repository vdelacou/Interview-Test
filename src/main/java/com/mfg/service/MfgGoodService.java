package com.mfg.service;

import com.mfg.model.MfgGood;

import java.util.List;

/**
 * @PackageName com.mfg.service
 * @Author Clark
 * @Date 2016/9/20
 * @Description
 */
public interface MfgGoodService {
    List<MfgGood> getGoods(Integer page, Integer size);

    Boolean saveGood(String name, Integer age);

    Boolean updateGood(Long id, String name, Integer age);

    Boolean deleteGood(Long id);

    MfgGood getGoodById(Long id);
}
