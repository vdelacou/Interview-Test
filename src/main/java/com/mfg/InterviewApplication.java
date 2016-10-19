package com.mfg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.CustomValidatorBean;

import javax.validation.Validator;

@SpringBootApplication
@EnableMongoAuditing
@EnableMongoRepositories
public class InterviewApplication {


	@Bean
	@ConditionalOnMissingBean
	public Validator defaultValidator(){
		return new CustomValidatorBean();
	}

	@Bean
	public ValidatingMongoEventListener validatingMongoEventListener(Validator validator) {
		return new ValidatingMongoEventListener(validator);
	}
	public static void main(String[] args) {
		SpringApplication.run(InterviewApplication.class, args);
	}
}
