package com.group.configuration;


import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.context.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.MultipartConfigElement;

@Configuration
@ComponentScan(basePackages = "com.group")
@PropertySource({"classpath:application.properties"})
public class UnicastConfiguration {

    private MultipartProperties multipartProperties;

//    @Bean
//    @ConditionalOnMissingBean({ MultipartConfigElement.class,
//            CommonsMultipartResolver.class })
//    public MultipartConfigElement multipartConfigElement() {
//        return this.multipartProperties.createMultipartConfig();
//    }
}
