package com.mfg.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.CustomValidatorBean;

import javax.validation.Validator;

/**
 * Created by lw on 16/12/7.
 */
@Configuration
public class ValidatorConfiguration {

//    private final String messageSourceName = "classpath:rest-messages.properties";

//    @Bean
//    public MessageSource messageSource(){
//        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
//        source.addBasenames(messageSourceName);
//        return source;
//    }

    @Bean
    @ConditionalOnMissingBean
    public Validator defaultValidator(){
        return new CustomValidatorBean();
    }

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener(Validator validator) {
        return new ValidatingMongoEventListener(validator);
    }
}
