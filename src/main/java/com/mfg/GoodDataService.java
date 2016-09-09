/**
 * Author: Dustin Mao
 */
package com.mfg;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GoodDataService {

	// temporary solution for prototype. there is duplicate key problem after server is restarted
	static long idCount = 0;
	
	@Autowired
	private GoodRepository goodRepository;
	 	 
	public Good create(Map<String, Object> goodMap) {    		
        long id = idCount++;
        Good good = new Good(id, goodMap.get("name").toString(), 
        					 Integer.parseInt(goodMap.get("age").toString()));

        goodRepository.save(good);

        return good;        
    }
	
	public Good get(Long id) {		
		return goodRepository.findById(id);
	}
	
	public List<Good> getAll() {		
		return goodRepository.findAll();
	}
	
	public Good update(Long id, Map<String, Object> goodMap) {		
		Good good = goodRepository.findById(id);
		
		// assumption: requestBody should not contain "id", since "id" is unlikely to be updated
		good.setName(goodMap.get("name").toString());
		good.setAge(Integer.parseInt(goodMap.get("age").toString()));
		goodRepository.delete(good); // delete it to workaround the duplicate key problem
		goodRepository.save(good);

        return good;
	}
	
	public void delete(Long id) {	
		Good good = goodRepository.findById(id);
		goodRepository.delete(good);
	}
}
