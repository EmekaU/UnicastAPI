package com.group.unicast;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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

//@Component
//class TableLister implements ApplicationRunner {
//    private static Logger log = LogManager.getLogger(UnicastApplication.class.getName());
//    private final DataSource dataSource;


@Component
class CustomerLister implements ApplicationRunner {
      private static Logger log = LogManager.getLogger(UnicastApplication.class.getName());
//      private final DataSource dataSource;
        private final JdbcTemplate jdbc;

//    CustomerLister(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    CustomerLister(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

//    @Override public void run(ApplicationArguments args) throws Exception {
//        String query = "SELECT * FROM user";
//        try (Connection con = dataSource.getConnection();
//             Statement stmt = con.createStatement();
//        ResultSet rs = stmt.executeQuery(query)) {
//            while (rs.next()) {
//                log.info("User [name={}]",rs.getString(1));
//            }
//        }
//    }
    @Override
    public void run(ApplicationArguments args){
        String query = "SELECT * FROM user";
        jdbc.query(query, resultSet -> {

            log.info("User[id={}]",
                    resultSet.getString(1));
        });

    }
}