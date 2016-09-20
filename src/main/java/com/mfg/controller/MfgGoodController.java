package com.mfg.controller;

import com.mfg.model.MfgGood;
import com.mfg.service.MfgGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @PackageName com.mfg.controller
 * @Author Clark
 * @Date 2016/9/20
 * @Description
 */
@Controller
@RequestMapping("/api")
public class MfgGoodController {
    @Autowired
    private MfgGoodService mfgGoodService;

    /**
     * get the list of 10 most recent Goods, then can access next one by /api/good?page=1&size=10
     *
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/good", method = RequestMethod.GET)
    public
    @ResponseBody
    List<MfgGood> getGoods(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return mfgGoodService.getGoods(page, size);
    }

    /**
     * post by json in body a new Good to create it
     *
     * @param name
     * @param age
     * @return
     */
    @RequestMapping(value = "/good", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String saveGood(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        String msg = "failed";
        if (mfgGoodService.saveGood(name, age)) {
            msg = "success";
        }
        return msg;
    }

    /**
     * put by json in body an existing Good to update it
     *
     * @param name
     * @param age
     * @param id
     * @return
     */
    @RequestMapping(value = "/good", method = RequestMethod.PUT, consumes = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String updateGood(@RequestParam("name") String name, @RequestParam("age") Integer age, @RequestParam("id") Long id) {
        String msg = "failed";
        if (mfgGoodService.updateGood(id, name, age)) {
            msg = "success";
        }
        return msg;
    }

    /**
     * delete a Good by it's id
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/good/{id}", method = RequestMethod.DELETE)
    public String deleteGood(@PathVariable("id") Long id) {
        String msg = "failed";
        if (mfgGoodService.deleteGood(id)) {
            msg = "success";
        }
        return msg;
    }

    /**
     * get a good by it's id
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/good/{id}", method = RequestMethod.GET)
    public MfgGood getGood(@PathVariable("id") Long id) {
        return mfgGoodService.getGoodById(id);
    }
}
