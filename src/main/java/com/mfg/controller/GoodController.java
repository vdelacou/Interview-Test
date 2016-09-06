package com.mfg.controller;

import com.mfg.JsonResult;
import com.mfg.model.Good;
import com.mfg.service.GoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jeffrey on 9/6/16.
 * @project interview
 */
@RestController
public class GoodController {
    private Logger logger = LoggerFactory.getLogger(GoodController.class);

    @Autowired
    private GoodService goodService;

    @RequestMapping("/api/good")
    public JsonResult getGoods(@RequestParam int page,
                               @RequestParam int size) {
        JsonResult result = new JsonResult();
        try {
            List<Good> res = goodService.getGoods(page, size);
            result.setResult(res);
            result.setMsg("get goods succeed");
            result.setStatusCode(HttpStatus.OK.value());
        } catch (Exception e) {
            logger.error("error happened in getting goods with {},{}", page, size, e);
            result.setMsg(e.getMessage());
            result.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return result;
    }

    @RequestMapping(value = "/api/good", method = RequestMethod.POST)
    public JsonResult addGood(@RequestBody Good good) {
        JsonResult result = new JsonResult();
        try {
            goodService.addGood(good);
            result.setMsg("add good succeed");
            result.setStatusCode(HttpStatus.OK.value());
        } catch (Exception e) {
            logger.error("error happened in adding good with {}", good, e);
            result.setMsg(e.getMessage());
            result.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return result;
    }

    @RequestMapping(value = "/api/good", method = RequestMethod.PUT)
    public JsonResult updateGood(@RequestBody Good good) {
        JsonResult result = new JsonResult();
        try {
            goodService.updateGood(good);
            result.setMsg("update good succeed");
            result.setStatusCode(HttpStatus.OK.value());
        } catch (Exception e) {
            logger.error("error happened in updating good with {}", good, e);
            result.setMsg(e.getMessage());
            result.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return result;
    }

    @RequestMapping(value = "/api/good/{id}", method = RequestMethod.DELETE)
    public JsonResult deleteGoods(@PathVariable("id") int id) {
        JsonResult result = new JsonResult();
        try {
            goodService.deleteGood(id);
            result.setMsg("delete good succeed");
            result.setStatusCode(HttpStatus.OK.value());
        } catch (Exception e) {
            logger.error("error happened in deleting good with {}", id, e);
            result.setMsg(e.getMessage());
            result.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return result;
    }

    @RequestMapping("/api/good/{id}")
    public JsonResult getGoods(@PathVariable("id") int id) {
        JsonResult result = new JsonResult();
        try {
            Good good = goodService.getGood(id);
            result.setResult(good);
            result.setMsg("get good succeed");
            result.setStatusCode(HttpStatus.OK.value());
        } catch (Exception e) {
            logger.error("error happened in getting good with {}", id, e);
            result.setMsg(e.getMessage());
            result.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return result;
    }
}
