package com.mfg.service;

import java.util.List;

import com.mfg.domain.Good;

public interface GoodService {
	public List<Good> getGoodList(int page,int size);
	public void createGood(Good good);
	public void updateGood(Good good);
	public void deleteGoodById(String goodId);
	public Good getGoodById(String goodId);
}
