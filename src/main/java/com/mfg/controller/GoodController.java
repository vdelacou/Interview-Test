package com.mfg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mfg.dto.FindGoodsReqDto;
import com.mfg.dto.GoodRespDto;
import com.mfg.dto.NewGoodReqDto;
import com.mfg.dto.UpdGoodReqDto;
import com.mfg.service.GoodService;

@RestController
@RequestMapping("/api")
public class GoodController {
  @Autowired
  private GoodService goodService;

  @RequestMapping(value = "/good", method = RequestMethod.GET)
  public List<GoodRespDto> findGoods(@RequestBody FindGoodsReqDto f) {
    return goodService.findGoods(f);
  }

  @RequestMapping(value = "/good", method = RequestMethod.POST)
  public boolean newGood(@RequestBody NewGoodReqDto goodReqDto) {
    return goodService.newGood(goodReqDto);
  }

  @RequestMapping(value = "/good", method = RequestMethod.PUT)
  public boolean updGood(@RequestBody UpdGoodReqDto updGoodReqDto) {
    return goodService.updGood(updGoodReqDto);
  }

  @RequestMapping(value = "/good/{id}", method = RequestMethod.DELETE)
  public boolean delGood(@PathVariable("id") String id) {
    return goodService.delGood(id);
  }

  @RequestMapping(value = "/good/{id}", method = RequestMethod.GET)
  public GoodRespDto findGood(@PathVariable("id") String id) {
    return goodService.findGood(id);
  }
}
