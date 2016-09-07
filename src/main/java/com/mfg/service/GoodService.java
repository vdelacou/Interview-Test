package com.mfg.service;

import java.util.List;

import com.mfg.entity.Good;

/**
 * 
 * @author duand
 *
 */
public interface GoodService {

	public List<Good> getGoodList(Integer page, Integer size) throws Exception;
	public String createGood(Good good) throws Exception;
	public void updateGood(Good good) throws Exception;
	public void deleteGood(String id) throws Exception;
	public Good getGood(String id) throws Exception;
}
