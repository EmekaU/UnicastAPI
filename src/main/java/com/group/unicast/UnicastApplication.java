package com.group.unicast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan(basePackages = {"com.group"})
@PropertySource({"classpath:application.properties"})
public class UnicastApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
//        System.setProperty("server.servlet.context-path", "");
        SpringApplication.run(UnicastApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(UnicastApplication.class);
    }

}
