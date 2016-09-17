package com.mfg.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mfg.domain.Good;
import com.mfg.service.GoodService;

@RestController
@RequestMapping("api/good")
public class GoodController {
	
	@Resource
	private GoodService goodService;
	
	/**
	 * get the list of 10 most recent Goods, then can access next one by /api/good?page=1&size=10
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,produces="application/json")
	public List<Good> getGoodList(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "10") Integer size){
		return goodService.getGoodList(page, size);
	}
	
	/**
	 * post by json in body a new Good to create it
	 * @param good
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,consumes="application/json")
	public ResponseEntity<String> createGood(Good good){
		try{
			goodService.createGood(good);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method=RequestMethod.PUT,consumes="application/json")
	public ResponseEntity<String> updateGood(Good good){
		try{
			goodService.updateGood(good);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="{goodId}",method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteGoodById(@PathVariable("goodId")String goodId){
		try{
			goodService.deleteGoodById(goodId);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="{goodId}",method=RequestMethod.GET,produces="application/json")
	public Good getGoodById(@PathVariable("goodId")String goodId){
		return getGoodById(goodId);
	}
}
