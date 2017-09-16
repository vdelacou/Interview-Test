package com.mfg.service;

import com.mfg.domain.Good;
import com.mfg.domain.GoodCreateDto;
import com.mfg.domain.GoodUpdateDto;

/**
 * Created by hushuming on 2017/1/14.
 */
public interface GoodService {

    // validate json data when creating
    void createValidate(GoodCreateDto dto);

    // validate json data when updating
    void updateValidate(GoodUpdateDto dto);

    // convert GoodCreateDto to Good
    Good createDtoToGood(GoodCreateDto dto);

}
