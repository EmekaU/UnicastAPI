package com.group.configuration;


import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@ComponentScan(basePackages = "com.group")
@PropertySource({"classpath:application.properties"})
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class UnicastConfiguration extends WebSecurityConfigurerAdapter {

}
