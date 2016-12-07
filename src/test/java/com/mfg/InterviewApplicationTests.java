package com.mfg;

import com.mfg.po.Good;
import com.mfg.vo.BasicResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import static com.mfg.vo.BasicResponse.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InterviewApplicationTests {

	private final RestTemplate restTemplate = new RestTemplate();

	@LocalServerPort()
	private int port;

	private int inc;

	private String getUrl(String path){
		return new StringBuffer("http://localhost:")
				.append(port)
				.append("/api/good")
				.append(path)
				.toString();
	}

	private String getUrl(){
		return getUrl("");
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void add(){
		BasicResponse<Good> rst = addGoods();
		Assert.assertEquals(rst.getCode(),RESPONSE_CODE_SUCCESS);
	}



	@Test
	public void update(){
		BasicResponse<Good> rst = addGoods();
		String goodId = rst.getData().getId();

		Assert.assertEquals(rst.getCode(),RESPONSE_CODE_SUCCESS);

		Integer newAge = rst.getData().getAge()+1000;
		Good newGoods = rst.getData();
		newGoods.setAge(newAge);

		restTemplate.put(getUrl(),newGoods);

		BasicResponse<Good> getRst = getById(goodId);

		Assert.assertEquals(getRst.getCode(),RESPONSE_CODE_SUCCESS);
		Assert.assertEquals(getRst.getData().getAge(),newAge);
	}


	@Test
	public void delete(){
		BasicResponse<Good> rst = addGoods();
		String goodId = rst.getData().getId();
		Assert.assertEquals(rst.getCode(),RESPONSE_CODE_SUCCESS);

		BasicResponse<Good> getRst = getById(goodId);

		Assert.assertEquals(getRst.getCode(),RESPONSE_CODE_SUCCESS);

		restTemplate.delete(
				getUrl(
						new StringBuffer("/")
								.append(goodId)
								.toString()));

		getRst = getById(goodId);
		Assert.assertEquals(getRst.getCode(),RESPONSE_CODE_FAIL);

	}

	@Test
	public void goodList(){
		Map<String,String> params = new HashMap<String,String>();
		params.put("page","1");
		params.put("size","10");
		BasicResponse rst = restTemplate.getForObject(
				getUrl(),GoodsResponse.class,params
		);
		Assert.assertEquals(rst.getCode(),RESPONSE_CODE_SUCCESS);
	}

	private Good generateTestGoodsEntity(){
		inc++;
		Good good = new Good();
		good.setAge(inc+10);
		good.setName(
				new StringBuffer("test-name-")
						.append(System.currentTimeMillis()).append("-")
						.append(inc).toString());
		return good;
	}

	private BasicResponse<Good> addGoods(){
		Good good = generateTestGoodsEntity();
		return restTemplate.postForObject(
				getUrl(),
				good,
				GoodsResponse.class);
	}

	public BasicResponse<Good> getById(String goodId){
		return restTemplate.getForObject(
				getUrl(
						new StringBuffer("/")
								.append(goodId)
								.toString()),GoodsResponse.class
		);
	}

}
