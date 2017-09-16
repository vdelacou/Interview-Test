package com.mfg.web;

import com.mfg.core.ApiEntity;
import com.mfg.core.ApiStatus;
import com.mfg.core.SpringDataPageable;
import com.mfg.domain.Good;
import com.mfg.repository.GoodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ^_^
 * Created by gaojiawei on 2016/9/30.
 */
@RestController
public class GoodController {

    private static final Logger logger = LoggerFactory.getLogger(GoodController.class);

    @Autowired
    GoodRepository goodRepository;

    @ResponseBody
    @RequestMapping(value = "/good/{id}", method = RequestMethod.GET)
    public ApiEntity<Good> getGoodById(@PathVariable("id") String id) {
        Good goodItem = goodRepository.findById(id);
        return new ApiEntity<Good>(goodItem);
    }

    @RequestMapping(value = "/good", method = RequestMethod.POST)
    public ApiEntity inserGood(@RequestBody Good good) {
        try {
            goodRepository.insert(good);
        } catch (Exception e) {
            logger.error("nserGood()---> error",e);
            return new ApiEntity(ApiStatus.STATUS_500);
        }
        return new ApiEntity(ApiStatus.STATUS_204);
    }

    @ResponseBody
    @RequestMapping(value="/good", method=RequestMethod.PUT)
    public ApiEntity updateGood(@RequestBody Good good){
        try{
            good.setProductionDate(new Date());
            goodRepository.save(good);
        }
        catch(Exception e){
            logger.error("nserGood()---> error",e);
            return new ApiEntity(ApiStatus.STATUS_500);
        }
        return new ApiEntity(ApiStatus.STATUS_204);
    }

    @RequestMapping(value="/good/{id}", method=RequestMethod.DELETE)
    public ApiEntity deleteGood(@PathVariable("id") String id){

        try{
            Good goodtime = new Good(id);
            goodRepository.delete(goodtime);
        }
        catch(Exception e){
            logger.error("nserGood()---> error",e);
            return new ApiEntity(ApiStatus.STATUS_500);
        }
        return new ApiEntity(ApiStatus.STATUS_204);
    }

    @RequestMapping(value="/good",method=RequestMethod.GET)
    public ApiEntity<List<Good>> getGoods(@RequestParam(value="page",required=true) Integer page,
                               @RequestParam(value="size",required=true) Integer size){
        List<Good> goodList = null;
        try{
            SpringDataPageable pager = new SpringDataPageable();
            List<Sort.Order> orders = new ArrayList<Sort.Order>();
            orders.add(new Sort.Order(Sort.Direction.ASC, "productionDate"));
            pager.setSort(new Sort(orders));
            pager.setPagenumber(page);
            pager.setPagesize(size);
            goodList = goodRepository.findAll(pager).getContent();
        }
        catch(Exception e){
            logger.error("nserGood()---> error",e);
            return new ApiEntity(ApiStatus.STATUS_500);
        }
        return new ApiEntity<>(goodList);
    }


}
