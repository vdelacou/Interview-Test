/**
 * Author: Dustin Mao
 */
package com.mfg;

import org.springframework.web.bind.annotation.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(value="/api/good")
public class GoodController {

  // hello world test
  @RequestMapping("/hello")
  public String getHello() {
    return "Hello World";
  }

  @Autowired
  GoodDataService goodDataService;

  @RequestMapping(value="/{id}", method=RequestMethod.GET) 
  public Good getGood(@PathVariable Long id) {
    return goodDataService.get(id);
  } 

  @RequestMapping(method=RequestMethod.GET) 
  public Map<String, Object> getAllGoods() {
    List<Good> goods = goodDataService.getAll();	
    Map<String, Object> response = new LinkedHashMap<String, Object>();
    response.put("totalGoods", goods.size());
    response.put("goods", goods);
    return response;
  }

  @RequestMapping(method=RequestMethod.POST) 
  public Map<String, Object> createGood(@RequestBody Map<String, Object> goodMap) {
    Good good = goodDataService.create(goodMap);

    Map<String, Object> response = new LinkedHashMap<String, Object>();
    response.put("message", "Good created successfully");
    response.put("good", good);

    return response;
  }

  @RequestMapping(value="/{id}", method=RequestMethod.PUT) 
  public Map<String, Object> createGood(@PathVariable Long id, @RequestBody Map<String, Object> goodMap) {
    Good good = goodDataService.update(id, goodMap);

    Map<String, Object> response = new LinkedHashMap<String, Object>();
    response.put("message", "Good updated successfully");
    response.put("good", good);

    return response;
  }

  @RequestMapping(value="/{id}", method=RequestMethod.DELETE) 
  public Map<String, String> deleteGood(@PathVariable Long id) { 
    goodDataService.delete(id);
    
    Map<String, String> response = new LinkedHashMap<String, String>();
    response.put("message", "Good deleted successfully");

    return response;
  } 
}
