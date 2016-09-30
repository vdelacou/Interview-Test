package com.mfg;

import com.mfg.core.SpringDataPageable;
import com.mfg.domain.Good;
import com.mfg.repository.GoodRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InterviewApplicationTests {
	@Autowired
	GoodRepository goodRepository;
	@Test
	public void testInsert() {
		Good g=new Good();
		g.setAge(1);
		g.setName("aa");
		g.setProductionDate(new Date());
		System.out.println("InterviewApplicationTests.testInsert()-------------->>>>>" +goodRepository.save(g));
	}

	@Test
	public void getGoodById() {
		System.out.println("InterviewApplicationTests.get()-------------->>>>>" + goodRepository.findById("57ee3fbcd944931ab81ac236"));
	}

	@Test
	public void getGoods(){
		List<Good> goodList = null;
			SpringDataPageable pager = new SpringDataPageable();
			List<Sort.Order> orders = new ArrayList<Sort.Order>();
			orders.add(new Sort.Order(Sort.Direction.ASC, "productionDate"));
			pager.setSort(new Sort(orders));
			pager.setPagenumber(1);
			pager.setPagesize(10);
			goodList = goodRepository.findAll(pager).getContent();
		System.out.println("InterviewApplicationTests.getGoods()-------------->>>>>" + goodList);
		}
	
}
