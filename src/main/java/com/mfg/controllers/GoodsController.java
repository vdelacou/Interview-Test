package com.mfg.controllers;

import com.mfg.BeanUtil;
import com.mfg.domains.Good;
import com.mfg.repositories.GoodsRepository;
import com.mongodb.*;

import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api")
public class GoodsController {
    @Resource
    private GoodsRepository goodsRepository;

    @RequestMapping(value = "/good/{page}/{size}",method = RequestMethod.GET)
    @ResponseBody
    public void listGood(HttpServletRequest request
            , @PathVariable("page") int page
            , @PathVariable("size") int size){
        Mongo mongo=new Mongo("localhost", 27017);
        DB db=mongo.getDB("test");
        DBCollection dbCollection=db.getCollection("good");
        DBCursor cursor=dbCollection.find().skip((page-1)*10).limit(size);
        try {
            while(cursor.hasNext()) {
                DBObject dbObject=cursor.next();
                System.out.println(dbObject.toString());
                System.out.println(dbObject.get("name"));
            }
        } finally {
            cursor.close();
        }
    }

    @RequestMapping(value = "/good/{id}",method = RequestMethod.GET)
    @ResponseBody
    public void getGood(HttpServletRequest request
            , @PathVariable("id") int id){
        Mongo mongo=new Mongo("localhost", 27017);
        DB db=mongo.getDB("test");
        DBCollection dbCollection=db.getCollection("good");
        BasicDBObject query = new BasicDBObject("id", id);
        DBCursor cursor=dbCollection.find(query);
        try {
            while(cursor.hasNext()) {
                DBObject dbObject=cursor.next();
                System.out.println(dbObject.toString());
                System.out.println(dbObject.get("name"));
            }
        } finally {
            cursor.close();
        }
    }

    @RequestMapping(value = "/addGood",method = RequestMethod.POST)
    public void addGood(HttpServletRequest request,
                           @RequestBody Good good) {
        Mongo mongo=new Mongo("localhost", 27017);
        DB db=mongo.getDB("test");
        DBCollection dbCollection=db.getCollection("good");
        try {
            dbCollection.insert(BeanUtil.bean2DBObject(good));
        } catch (Exception e){
            // ....
        }finally {
            mongo.close();
        }
    }

    @RequestMapping(value = "/updateGood",method = RequestMethod.PUT)
    public void updateGood(HttpServletRequest request,
                           @RequestBody Good good) {
        Mongo mongo=new Mongo("localhost", 27017);
        DB db=mongo.getDB("test");
        DBCollection dbCollection=db.getCollection("good");

        try {
            dbCollection.update(BeanUtil.bean2DBObject(good),
                    BeanUtil.bean2DBObject(good));
        } catch (Exception e){
            // ....
        }finally {
            mongo.close();
        }
    }

    @RequestMapping(value = "/deleteGood/{id}",method = RequestMethod.DELETE)
    public void deleteGood(HttpServletRequest request
            , @PathVariable("id") Integer id){
        Mongo mongo=new Mongo("localhost", 27017);
        DB db=mongo.getDB("test");
        DBCollection dbCollection=db.getCollection("good");
        BasicDBObject query = new BasicDBObject("id", id);
        try {
            dbCollection.remove(query);
        } catch (Exception e){
           // ....
        }finally {
            mongo.close();
        }
    }
}
