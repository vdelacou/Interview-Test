package com.mfg.service;

import com.mfg.InterviewApplication;
import com.mfg.model.Good;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

/**
 * @author jeffrey on 9/6/16.
 * @project interview
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class GoodServiceTest {
    @Autowired
    private GoodService goodService;

    @Test
    public void testGood() {
        goodService.deleteGood(1);

        Good good = new Good(1, "test", 10, new Date());
        goodService.addGood(good);

        good.setAge(11);
        goodService.updateGood(good);

        good = goodService.getGood(1);
        Assert.assertEquals(11, good.getAge());

        goodService.deleteGood(1);
    }
}
