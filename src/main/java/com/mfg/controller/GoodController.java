package com.mfg.controller;

import com.mfg.po.Good;
import com.mfg.repository.GoodRepository;
import com.mfg.vo.BasicResponse;
import com.mfg.vo.SimplePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import static com.mfg.vo.BasicResponse.*;
/**
 * Created by lw on 16/12/7.
 */
@RestController
public class GoodController {
    @Autowired
    private GoodRepository goodRepository;

    @RequestMapping(value = "/good",method = RequestMethod.GET)
    public BasicResponse<?> goodList(@RequestParam(value="page",required = false) Integer page,
 			@RequestParam(value="size",required = false) Integer size){


        SimplePage simplePage = new SimplePage();

        if(size!=null&&size>0)simplePage.setPageSize(size);
        if(page!=null&&page>0)simplePage.setPageNumber(page);

        Sort sort = getSort(new HashMap<String,Sort.Direction>(){{
            put("productionDate",Sort.Direction.DESC);
        }});

        simplePage.setSort(sort);

        Page<Good> pageRst = goodRepository.findAll(simplePage);
        return success(pageRst);
    }

    @RequestMapping(value = "good",method = RequestMethod.POST)
    public BasicResponse<?> add(@RequestBody @Validated Good good,
                    BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return error("data valid fail.");
        }

        Good matchGood = goodRepository.findByName(good.getName());
        if(matchGood!=null){
            return fail(
                    new StringBuffer("name [").
                    append(good.getName()).append("] exists ").
                    toString());
        }

        good = goodRepository.insert(good);
        return success(good);
    }

    @RequestMapping(value = "good",method = RequestMethod.PUT)
    public BasicResponse<?> update(@RequestBody @Validated Good good,
                                BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return error("data valid fail.");
        }

        Good matchGood = goodRepository.findByName(good.getName());
        if(matchGood==null){
            return fail(
                    new StringBuffer("name [").
                            append(good.getName()).append("] not exists ").
                            toString());
        }

        good.setId(matchGood.getId());
        good = goodRepository.save(good);

        return success(good);
    }

    @RequestMapping(value = "good/{id}",method = RequestMethod.DELETE)
    public BasicResponse<?> del(@PathVariable String id){

        goodRepository.delete(id);
        return success(null);

    }

    @RequestMapping(value = "good/{id}",method = RequestMethod.GET)
    public BasicResponse<?> getById(@PathVariable String id){

        Good good = goodRepository.findOne(id);

        if(good==null)return fail(
                new StringBuffer("good id [")
                        .append(id)+"] does not exist");

        return success(good);
    }

    private Sort getSort(Map<String,Sort.Direction> sort){

        if(sort==null)return null;

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        sort.forEach(new BiConsumer<String, Sort.Direction>() {
            @Override
            public void accept(String s, Sort.Direction d) {
                orders.add(new Sort.Order(d,s));
            }
        });

        return new Sort(orders);
    }


}
