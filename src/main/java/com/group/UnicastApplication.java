package com.group;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class UnicastApplication {

    public static void main(String[] args) {
//        System.setProperty("server.servlet.context-path", "");
        SpringApplication.run(UnicastApplication.class, args);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(UnicastApplication.class);
//    }


}