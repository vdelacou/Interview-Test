package com.mfg.rest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.mfg.entities.Good;
import com.mfg.repository.GoodRepository;
import com.mfg.result.JsonResult;
import com.mfg.result.ResultCode;
import com.mfg.result.SpringDataPageable;

import org.springframework.data.domain.Page;  
import org.springframework.data.domain.Sort;  
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;  

@RestController
public class GoodController {

	@Autowired
	GoodRepository goodRepository;
	
	
	
	@ResponseBody
	@RequestMapping(value="/good/{id}",method=RequestMethod.GET)
	public Good getGoodById(@PathVariable("id") String id){
		Good goodItem = null;		
		System.out.println("getGoodById:"+id);
		goodItem = goodRepository.findById(id);
		System.out.println("getGoodById:"+goodItem);
		return goodItem;
	}
	
	@ResponseBody
	@RequestMapping(value="/good",method=RequestMethod.GET)
	public List<Good> getGoods(@RequestParam(value="page",required=true) Integer page,
			@RequestParam(value="size",required=true) Integer size){
		List<Good> goodList = null;	
		Page<Good> pGood = null;
		System.out.println("getGoods:"+page+":size:"+size);
		SpringDataPageable pager = new SpringDataPageable();
		List<Order> orders = new ArrayList<Order>();  
		orders.add(new Order(Direction.ASC, "productionDate"));  
		pager.setSort(new Sort(orders));  
		
		pager.setPagenumber(page);
		pager.setPagesize(size);
		pGood = goodRepository.findAll(pager);
		goodList = new ArrayList<Good>();
		for(Good item: pGood){
			goodList.add(item);
		}
		return goodList;
	}
	
	@ResponseBody
	@RequestMapping(value="/good/{id}", method=RequestMethod.DELETE)
	public JsonResult deleteGood(@PathVariable("id") String id){
		System.out.println("deleteGood:"+id);
		JsonResult result = new JsonResult();
		Good goodtime = new Good();
		goodtime.setId(id);
		try{
			goodRepository.delete(goodtime);
		}
		catch(Exception e){
			result.setCode(ResultCode.ERROR);
			result.setMessage("fail!");
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/good", method=RequestMethod.PUT)
	public JsonResult updateGood(@RequestBody Good good){
		System.out.println("updateGood:"+good);
		JsonResult result = new JsonResult();
		good.setProductionDate(new Date());
		try{
			goodRepository.save(good);
		}
		catch(Exception e){
			result.setCode(ResultCode.ERROR);
			result.setMessage("fail!");
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/good", method=RequestMethod.POST)
	public JsonResult  inserGood(@RequestBody Good good){
		JsonResult result = new JsonResult();
		System.out.println(good);
		try{
			goodRepository.insert(good);
		}
		catch(Exception e){
			result.setCode(ResultCode.ERROR);
			result.setMessage("fail!");
		}
		return result;
	}
	
}
