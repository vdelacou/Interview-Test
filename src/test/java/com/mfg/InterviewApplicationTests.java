package com.mfg;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mfg.model.Good;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class InterviewApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	/**
	 * Reset all records before each test
	 */
	@Before
	@Test
	public void reset() {
		while (true) {
			Good[] goods = this.restTemplate.getForEntity("/api/good",
					Good[].class).getBody();
			for (Good good : goods) {
				this.restTemplate.delete(String.format("/api/good/%s",
						good.getId()));
			}
			if (goods.length == 0) {
				break;
			}
		}
	}

	/**
	 * Test create and get list
	 */
	@Test
	public void testCreate() {
		int total = (int) (Math.floor(Math.random() * 20) + 10);
		int count = total;
		while (count > 0) {
			Good good = new Good();
			good.setName(String.format("good %d", count));
			good.setAge(0);
			good.setProductionDate(new Date());
			this.restTemplate.postForEntity("/api/good", good, Good.class);
			count--;
		}

		Good[] tenGoods = this.restTemplate
				.getForEntity(String.format("/api/good", total), Good[].class).getBody();

		assertThat(tenGoods.length).isEqualTo(10);
		
		Good[] goods = this.restTemplate
				.getForEntity(String.format("/api/good?page=0&size=%d", total), Good[].class).getBody();

		assertThat(goods.length).isEqualTo(total);
		
	};
	
	/**
	 * Test duplicate name in Good
	 */
	@Test
	public void testCreateDuplicateName(){
		Good good1 = new Good();
		good1.setName(String.format("good %d", 1));
		good1.setAge(0);
		good1.setProductionDate(new Date());
		this.restTemplate.postForEntity("/api/good", good1, Good.class);
		
		ResponseEntity entity = this.restTemplate.postForEntity("/api/good", good1, Good.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		
	}
	
	/**
	 * Test malformat and corner cases
	 */
	@Test
	public void testCreateMalformat(){
		Good bad = new Good();
		ResponseEntity entity;
		
		entity = this.restTemplate.postForEntity("/api/good", bad, Good.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		
		bad.setName(null);
		bad.setAge(0);
		bad.setProductionDate(new Date());
		entity = this.restTemplate.postForEntity("/api/good", bad, Good.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		

		bad.setName(new String(new char[51]).replace('\0', ' '));
		bad.setAge(0);
		bad.setProductionDate(new Date());
		entity = this.restTemplate.postForEntity("/api/good", bad, Good.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		
		bad.setName(new String(new char[50]).replace('\0', ' '));
		bad.setAge(-1);
		bad.setProductionDate(new Date());
		entity = this.restTemplate.postForEntity("/api/good", bad, Good.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		
		bad.setName(new String(new char[50]).replace('\0', ' '));
		bad.setAge(0);
		bad.setProductionDate(null);
		entity = this.restTemplate.postForEntity("/api/good", bad, Good.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		
		bad.setName(new String(new char[50]).replace('\0', ' '));
		bad.setAge(0);
		bad.setProductionDate(new Date(new Date().getTime() + 10000));
		entity = this.restTemplate.postForEntity("/api/good", bad, Good.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	/**
	 * Test update good
	 */
	@Test
	public void testUpdate() {
		Good good = new Good();
		good.setName("good");
		good.setAge(0);
		good.setProductionDate(new Date());
		
		Good saved = this.restTemplate.postForEntity("/api/good", good, Good.class).getBody();
		good.setId(saved.getId());
		good.setAge(1);
		this.restTemplate.put("/api/good", good);
		
		Good updated = this.restTemplate.getForObject(String.format("/api/good/%s", saved.getId()), Good.class);
		assertThat(updated.getAge()).isEqualTo(good.getAge());
	}

	/**
	 * Test delete
	 */
	@Test
	public void testDelete() {
		Good good = new Good();
		good.setName("good");
		good.setAge(0);
		good.setProductionDate(new Date());
		
		Good saved = this.restTemplate.postForEntity("/api/good", good, Good.class).getBody();

		this.restTemplate.delete(String.format("/api/good/%s", saved.getId()));

		Good shouldNotExist = this.restTemplate.getForObject(String.format("/api/good/%s", saved.getId()), Good.class);
		assertThat(shouldNotExist).isNull();
		
		
	}
}
