package com.mfg.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mfg.dao.GoodDao;
import com.mfg.domain.Good;
import com.mfg.service.GoodService;

@Service("goodService")
@Transactional
public class GoodServiceImpl implements GoodService {
	
	@Resource
	private GoodDao goodDao;
	
	@Override
	public List<Good> getGoodList(int page, int size) {
		List<Good> goods = goodDao.queryList((page-1)*size, size);
		if(goods==null||goods.size()==0){
			goods = Lists.newArrayList();
		}
		return goods;
	}

	@Override
	public void createGood(Good good) {
		goodDao.insertGood(good);
	}

	@Override
	public void updateGood(Good good) {
		goodDao.updateGood(good);
	}

	@Override
	public void deleteGoodById(String goodId) {
		goodDao.deleteGoodById(goodId);
	}

	@Override
	public Good getGoodById(String goodId) {
		Good good = goodDao.getGoodById(goodId);
		if(good==null) good = new Good();
		return good;
	}

}
