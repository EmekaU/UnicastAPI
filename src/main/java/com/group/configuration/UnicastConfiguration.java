package com.group.configuration;


import org.apache.catalina.security.SecurityConfig;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.HttpCookie;
import org.springframework.http.MediaType;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.http.HttpSession;

@Configuration
@ComponentScan(basePackages = "com.group")
@PropertySource({"classpath:application.properties"})
//@EnableRedisHttpSession // uses Filter to replace HttpSession to Redis implementation.
public class UnicastConfiguration{

//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory(){
//        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("server", 6379));
//    }

//    @Bean
//    public LettuceConnectionFactory connectionFactory(){
////        Connect spring session to redis server. Default - (server, 6379)
//        return new LettuceConnectionFactory();
//    }
//
//    @Bean
//    public HttpSessionIdResolver httpSessionIdResolver(){
////        Customizes HttpSession to use headers to convey the current session info. instead of cookies
//        return HeaderHttpSessionIdResolver.xAuthToken();
//    }
//
//    @Bean
//    ConfigureRedisAction configureRedisAction(){
////        Prevent session from configuring redis keyspace events
//        return ConfigureRedisAction.NO_OP;
//    }

}
//
//@EnableWebSecurity
//class SecurityConfigurer extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity security) throws Exception{
//        security.httpBasic().disable();
//    }
//}
