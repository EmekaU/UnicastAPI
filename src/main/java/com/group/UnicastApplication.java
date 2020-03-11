package com.group;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SpringBootApplication
public class UnicastApplication extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
//        System.setProperty("server.servlet.context-path", "");
        SpringApplication.run(UnicastApplication.class, args);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/user/create", "/user/authenticate");
    }

    @Override
    protected void configure (HttpSecurity http) throws Exception{
        http.httpBasic()
                .and()
                    .authorizeRequests()
                    .antMatchers("/user/*", "/podcast/*", "/comment/*")
                    .authenticated() // permit authenticated users
                .and()
                .csrf()
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true);
        http.cors();
//        http.sessionManagement().maximumSessions(2);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(UnicastApplication.class);
//    }


}

//@Component
//class TableLister implements ApplicationRunner {
//    private static Logger log = LogManager.getLogger(UnicastApplication.class.getName());
//    private final DataSource dataSource;

//
//@Component
//class CustomerLister implements ApplicationRunner {
//      private static Logger log = LogManager.getLogger(UnicastApplication.class.getName());
////      private final DataSource dataSource;
//        private final JdbcTemplate jdbc;
//
////    CustomerLister(DataSource dataSource) {
////        this.dataSource = dataSource;
////    }
//
//    CustomerLister(JdbcTemplate jdbc) {
//        this.jdbc = jdbc;
//    }
//
////    @Override public void run(ApplicationArguments args) throws Exception {
////        String query = "SELECT * FROM user";
////        try (Connection con = dataSource.getConnection();
////             Statement stmt = con.createStatement();
////        ResultSet rs = stmt.executeQuery(query)) {
////            while (rs.next()) {
////                log.info("User [name={}]",rs.getString(1));
////            }
////        }
////    }
//    @Override
//    public void run(ApplicationArguments args){
//        String query = "SELECT * FROM user";
//        jdbc.query(query, resultSet -> {
//
//            log.info("User[id={}]",
//                    resultSet.getString(1));
//        });
//
//    }
//}
