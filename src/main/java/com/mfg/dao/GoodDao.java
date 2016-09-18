package com.mfg.dao;

import java.util.List;

import com.mfg.domain.Good;

public interface GoodDao {
	public List<Good> queryList(int skip,int limit);
	public void insertGood(Good good);
	public void updateGood(Good good);
	public void deleteGoodById(String id);
	public Good getGoodById(String id);
}
