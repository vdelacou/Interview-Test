package com.mfg.service;

import java.util.List;

import com.mfg.dto.FindGoodsReqDto;
import com.mfg.dto.GoodRespDto;
import com.mfg.dto.NewGoodReqDto;
import com.mfg.dto.UpdGoodReqDto;

public interface GoodService {

  /**
   * 分页获取goods
   * 
   * @param f page 0为首页 size
   * @return
   */
  List<GoodRespDto> findGoods(FindGoodsReqDto f);

  /**
   * 新建good
   * 
   * @param goodReqDto
   * @return true 成功 false 失败
   */
  boolean newGood(NewGoodReqDto goodReqDto);

  /**
   * 更新good
   * 
   * @param updGoodReqDto
   * @return
   */
  boolean updGood(UpdGoodReqDto updGoodReqDto);

  /**
   * 删除good
   * 
   * @param id good主键
   * @return
   */
  boolean delGood(String id);

  /**
   * 获取单个good
   * 
   * @param id good主键
   * @return
   */
  GoodRespDto findGood(String id);

}
