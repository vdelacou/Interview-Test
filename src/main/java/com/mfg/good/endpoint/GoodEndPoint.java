package com.mfg.good.endpoint;

import com.mfg.constant.Api;
import com.mfg.constant.ApiPrefix;
import com.mfg.good.domain.Good;
import com.mfg.good.service.GoodService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Endpoint for good management.
 *
 * @author Sand Wen
 * @since 2016.9.7
 */
@io.swagger.annotations.Api("good endpoint")
@RestController
@RequestMapping(value = ApiPrefix.Version1, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GoodEndPoint {
    @Autowired
    GoodService service;

    @ApiOperation("Get good list")
    @ResponseBody
    @RequestMapping(value = Api.Good.Goods, method = RequestMethod.GET)
    public Collection<Good> getGoodList(
            @ApiParam(value = "page", defaultValue = "1")
            @RequestParam("page") int page,
            @ApiParam(value = "size", defaultValue = "10")
            @RequestParam("size") int size) {
        return service.getGoodList(page - 1, size);
    }

    @ApiOperation("create good object")
    @ResponseBody
    @RequestMapping(value = Api.Good.Goods, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String createGood(@Valid @RequestBody Good good) {
        good.setId(ObjectId.get().toString());
        return service.createGood(good);
    }

    @ApiOperation("update an existing good")
    @ResponseBody
    @RequestMapping(value = Api.Good.Goods, method = RequestMethod.PUT)
    public ResponseEntity<Void> updateGood(@Valid @RequestBody Good good) {
        Good legacy = service.getGood(good.getId());
        if (legacy == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            service.saveGood(good);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation("delete specified good")
    @ResponseBody
    @RequestMapping(value = Api.Good.Good, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGood(@PathVariable("id") String id) {
        service.deleteGood(id);
    }

    @ApiOperation("Get good")
    @RequestMapping(value = Api.Good.Good, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Good> getGood(@PathVariable("id") String id) {
        Good good = service.getGood(id);
        if (good == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(good, HttpStatus.OK);
    }
}
