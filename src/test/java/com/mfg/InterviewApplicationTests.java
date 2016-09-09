package com.mfg;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.SpringApplication;  
import org.springframework.boot.test.SpringApplicationConfiguration;  
import org.springframework.mock.web.MockServletContext;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  
import org.springframework.test.context.web.WebAppConfiguration;  
import org.springframework.context.ConfigurableApplicationContext;  
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.Before; 
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.equalTo;  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; 

//@RunWith(SpringRunner.class)
//@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class InterviewApplicationTests {

	//@Test
	//public void contextLoads() {
	//	System.out.println("in InterviewApplicationTests::contextLoads");
	//}
	
	private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new GoodController()).build();
    }

    @Test
    public void testGetHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/good/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello World")));
    }
}
