package com.group.configuration;


import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "com.group")
@PropertySource({"classpath:application.properties"})
public class UnicastConfiguration {

}
