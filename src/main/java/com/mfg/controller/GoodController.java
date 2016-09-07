package com.mfg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mfg.entity.Good;
import com.mfg.service.GoodService;

/**
 * 
 * @author duand
 *
 */
@RestController  
@RequestMapping("/api/good") 
public class GoodController {
	
	@Autowired
	private GoodService goodService;

	/***
	 * get the list of 10 most recent Goods, then can access next one by /api/good?page=1&size=10
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> getGoodList(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
		try {
			return new ResponseEntity<String>(goodService.getGoodList(page, size).toString(), HttpStatus.OK); 
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * post by json in body a new Good to create it
	 * @param good
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> createGood(@RequestBody Good good) {
		try {
			return new ResponseEntity<String>(goodService.createGood(good), HttpStatus.OK); 
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * put by json in body an existing Good to update it
	 * @param good
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<String> updateGood(@RequestBody Good good) {
		try {
			goodService.updateGood(good);
			return new ResponseEntity<String>(HttpStatus.OK); 
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * delete a Good by it's id
	 * @param goodId
	 * @return
	 */
	@RequestMapping(value="/{goodId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteGood(@PathVariable("goodId") String goodId) {
		try {
			goodService.deleteGood(goodId);
			return new ResponseEntity<String>(HttpStatus.OK); 
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * get a good by it's id
	 * @param goodId
	 * @return
	 */
	@RequestMapping(value="/{goodId}", method = RequestMethod.GET)
	public ResponseEntity<String> getGood(@PathVariable("goodId") String goodId) {
		try {
			return new ResponseEntity<String>(goodService.getGood(goodId).toString(), HttpStatus.OK); 
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
