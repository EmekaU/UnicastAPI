//package com.group.configuration;
//
//
//import org.springframework.context.annotation.*;
//import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
//import org.springframework.http.MediaType;
//import org.springframework.web.servlet.config.annotation.*;
//
//@Configuration
//@PropertySource({"classpath:application.properties"})
////@EnableWebMvc
//public class UnicastConfiguration extends WebMvcConfigurerAdapter {
//
//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer){
//        configurer.favorPathExtension(false).
//                favorParameter(true).parameterName("alt").
//                ignoreAcceptHeader(true).useJaf(false).
//                defaultContentType(MediaType.APPLICATION_JSON).
//                mediaType("xml", MediaType.APPLICATION_XML).
//                mediaType("json", MediaType.APPLICATION_JSON);
//    }
//
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev(){
//        return new PropertySourcesPlaceholderConfigurer();
//    }
//}
