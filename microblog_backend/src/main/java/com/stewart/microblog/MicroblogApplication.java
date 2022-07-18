package com.stewart.microblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author Administrator
 */
@SpringBootApplication
public class MicroblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroblogApplication.class, args);
    }

}
