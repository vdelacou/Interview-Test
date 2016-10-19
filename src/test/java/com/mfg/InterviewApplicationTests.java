package com.mfg;

import com.mfg.domain.Good;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.rest.core.mapping.ResourceType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InterviewApplicationTests {
	private final Logger log = LoggerFactory.getLogger(InterviewApplicationTests.class);
	private final RestTemplate template = new RestTemplate();
	private final String SERVICE_URI = "http://localhost:%s/api/good";
	@LocalServerPort
	int port;

	private String getServiceUri(){
		return String.format(SERVICE_URI, port);
	}

	private String getId(Map<?,?> createReturnValue){
		return StringUtils.substringAfterLast((String)((Map)((Map)createReturnValue.get("_links")).get("self")).get("href"),"/");
	}

	@Test(expected = HttpClientErrorException.class)
	public void testPostGetDelete(){
		String name = "zhangsan";
		Map<?,?> createReturnValue = template.postForObject(getServiceUri(),new Good(name,25),Map.class);
		Assert.assertEquals(createReturnValue.get("name"),name);
		String id = getId(createReturnValue);
		String urlTemplate = getServiceUri() + "/{id}";
		createReturnValue = template.getForObject(urlTemplate,Map.class,id);
		Assert.assertEquals(createReturnValue.get("name"),name);
		template.delete(urlTemplate,id);

		template.getForObject(urlTemplate,Map.class,id);
	}



	@Test(expected=HttpServerErrorException.class)
	public void testNameLength() throws Exception{
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<50; i++) {
			sb.append(RandomStringUtils.randomAlphabetic(1));
		}
		Good good = new Good(sb.toString(),25);
		try{
			Map<?,?> createReturnValue = template.postForObject(getServiceUri(), good,Map.class);
			good.setId(getId(createReturnValue));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new Exception("非预期的异常");
		}
		good.setName(good.getName()+"1");
		template.put(getServiceUri(), good,Map.class);
	}

	@Test(expected=HttpServerErrorException.class)
	public void testAgeGtZore() throws Exception{
		Good good = new Good("lisi",0);
		try{
			Map<?,?> createReturnValue = template.postForObject(getServiceUri(), good,Map.class);
			good.setId(getId(createReturnValue));
		} catch (Exception e) {
			throw new Exception("非预期的异常");
		}
		good.setAge(-1);
		template.put(getServiceUri(), good);
	}


}
