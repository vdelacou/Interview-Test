package com.mfg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@EnableAutoConfiguration
@SpringBootApplication
public class InterviewApplication {
	public static void main(String[] args) {
		SpringApplication.run(InterviewApplication.class, args);
	}
}
