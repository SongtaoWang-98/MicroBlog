package com.stewart.microblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 启动类
 * @author 王松涛
 */
@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = {"com.stewart.microblog.esrepository"})
@EnableJpaRepositories(basePackages = {"com.stewart.microblog.repository"})
public class MicroblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroblogApplication.class, args);
    }

}
