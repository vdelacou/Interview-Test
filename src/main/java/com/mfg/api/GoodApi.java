package com.mfg.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.mfg.pojo.Good;
import com.mongodb.WriteResult;

@RestController
public class GoodApi {
	
	@Autowired  
    MongoTemplate template; 
	
	@RequestMapping(value = "/api/good", method = RequestMethod.GET)
    @ResponseBody
	public String goodList(HttpServletRequest request){
		String page = request.getParameter("page");
		String size = request.getParameter("size");
		int sizeint =  Integer.parseInt(size);
		int pageint =  Integer.parseInt(page);
		Query query = new Query();
		long cnt = template.count(query, Good.class);
		int skip = (pageint-1)*sizeint;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", cnt);
		map.put("list", null);
		if(cnt<skip){
			return JSON.toJSONString(map);
		}
		query.skip((pageint-1)*sizeint);
		query.limit(sizeint);
		List<Good> rl = template.find(query, Good.class);
		map.put("list", rl);
		return JSON.toJSONString(map);
	}
	
	@RequestMapping(value = "/api/good", method = RequestMethod.POST)
    @ResponseBody
	public String goodAdd(Good good){
		if(good.getAge()<0){
			return "false";
		}
		template.save(good);
		return "true";
	}
	
	@RequestMapping(value = "/api/good", method = RequestMethod.PUT)
    @ResponseBody
	public String goodUpdate(Good good){
		Query query = Query.query(Criteria.where("name").is(good.getName()));
		Update update = Update.update("age", good.getAge()).set("productionDate", good.getProductionDate());
		WriteResult wr = template.updateFirst(query, update, Good.class);
		if(null != wr&&wr.getN()>0){
			return "true";
		}
		return "false";
	}
	
	@RequestMapping(value =  "/api/good/{id}", method = RequestMethod.DELETE)
    @ResponseBody
	public String goodDel(@PathVariable("id") String id){
		Query query = Query.query(Criteria.where("_id").is(new ObjectId(id)));
		WriteResult wr = template.remove(query, Good.class);
		if(null != wr&&wr.getN()>0){
			return "true";
		}
		return "false";
	}
	
	@RequestMapping(value =  "/api/good/{id}", method = RequestMethod.GET)
    @ResponseBody
	public String goodGet(@PathVariable("id") String id){
		Good gd = template.findById(new ObjectId(id), Good.class);
		return JSON.toJSONString(gd);
	}
	
}
