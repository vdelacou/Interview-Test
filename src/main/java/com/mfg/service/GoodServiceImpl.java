package com.mfg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.mfg.dao.GoodPageable;
import com.mfg.dao.GoodRepository;
import com.mfg.entity.Good;
import com.mfg.entity.GoodConstraint;

/**
 * 
 * @author duand
 *
 */
@Service
public class GoodServiceImpl implements GoodService {

	@Autowired
	private GoodRepository goodRepo;
	
	/**
	 * 
	 */
	@Override
	public List<Good> getGoodList(Integer page, Integer size) throws Exception {
		Sort sort = new Sort(new Order(Direction.DESC, "productionDate"));
		GoodPageable goodPageable = new GoodPageable(page, size, sort);
		Page<Good> pageGoods = goodRepo.findAll(goodPageable);
		return pageGoods.getContent();
	}

	/**
	 * 
	 */
	@Override
	public String createGood(Good good) throws Exception {
		// check whether has the same name
		Good goodTmp = goodRepo.findByName(good.getName());
		if (goodTmp != null) {
			throw new Exception(String.format("one good with %s has existed", good.getName()));
		}
		
		// validate and save
		if (GoodConstraint.validate(good)) {
			goodRepo.save(good);
			return good.getId();
		} else {
			throw new Exception("invalid good, please check the content");
		}
	}

	/**
	 * 
	 */
	@Override
	public void updateGood(Good good) throws Exception {
		Good goodTmp = goodRepo.findOne(good.getId());
		if (goodTmp == null) {
			throw new Exception(String.format("good %s doesn't exist", good.getId()));
		}

		// check name
		if (!goodTmp.getName().equals(good.getName())) {
			goodTmp = goodRepo.findByName(good.getName());
			if (goodTmp != null) {
				throw new Exception(String.format("name %s has existed", good.getName()));
			}
		}

		// validate it
		if (GoodConstraint.validate(good)) {
			goodRepo.save(good);
		} else {
			throw new Exception("invalid good");
		}
	}

	/**
	 * 
	 */
	@Override
	public void deleteGood(String id) throws Exception {
		goodRepo.delete(id);
	}

	/**
	 * 
	 */
	@Override
	public Good getGood(String id) throws Exception {
		Good good = goodRepo.findOne(id);
		if (good != null) {
			return good;
		} else {
			throw new Exception("no good with id " + id);
		}
	}
}
