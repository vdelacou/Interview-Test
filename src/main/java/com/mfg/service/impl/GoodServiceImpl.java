package com.mfg.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mfg.dto.FindGoodsReqDto;
import com.mfg.dto.GoodRespDto;
import com.mfg.dto.NewGoodReqDto;
import com.mfg.dto.UpdGoodReqDto;
import com.mfg.model.Good;
import com.mfg.repository.GoodRepository;
import com.mfg.service.GoodService;

@Service
public class GoodServiceImpl implements GoodService {

  @Autowired
  private GoodRepository goodRepository;

  @Override
  public List<GoodRespDto> findGoods(FindGoodsReqDto goodReqDto) {
    int page = goodReqDto.getPage();
    if (page < 0) {
      page = 0;
    }
    int size = goodReqDto.getSize();
    if (size <= 0) {
      size = 10;
    }
    List<Good> goods = goodRepository.findGoodsByPage(page, size);
    List<GoodRespDto> goodRespDtos = new ArrayList<>();
    goods.forEach(g -> {
      GoodRespDto goodRespDto = new GoodRespDto();
      BeanUtils.copyProperties(g, goodRespDto);
      goodRespDtos.add(goodRespDto);
    });
    return goodRespDtos;
  }

  @Override
  public boolean newGood(NewGoodReqDto goodReqDto) {
    String name = goodReqDto.getName();
    if (StringUtils.isEmpty(name) || name.length() > 50) {
      // log...
      return false;
    }
    int age = goodReqDto.getAge();
    if (age < 0) {
      // log...
      return false;
    }
    Good good = new Good();
    good.setAge(age);
    good.setName(name);
    good.setProductionDate(new Date().getTime());
    goodRepository.saveOrUpdate(good);
    return true;
  }

  @Override
  public boolean updGood(UpdGoodReqDto updGoodReqDto) {
    String id = updGoodReqDto.getId();
    if (StringUtils.isEmpty(id)) {
      return false;
    }
    String name = updGoodReqDto.getName();
    if (StringUtils.isEmpty(name) || name.length() > 50) {
      // log...
      return false;
    }
    int age = updGoodReqDto.getAge();
    if (age < 0) {
      // log...
      return false;
    }

    Good good = goodRepository.findGoodById(id);
    if (good == null) {
      return false;
    }
    good.setAge(age);
    good.setName(name);
    good.setProductionDate(new Date().getTime());
    goodRepository.saveOrUpdate(good);

    return true;
  }

  @Override
  public boolean delGood(String id) {
    if (StringUtils.isEmpty(id)) {
      return false;
    }
    return goodRepository.delGoodById(id);
  }

  @Override
  public GoodRespDto findGood(String id) {
    if (StringUtils.isEmpty(id)) {
      return null;
    }
    Good good = goodRepository.findGoodById(id);
    if (good == null) {
      return null;
    }
    GoodRespDto goodRespDto = new GoodRespDto();
    BeanUtils.copyProperties(good, goodRespDto);
    return goodRespDto;
  }

}
