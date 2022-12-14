package com.stewart.microblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 * @author 王松涛
 */
@SpringBootApplication
@EnableScheduling
public class MicroblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroblogApplication.class, args);
    }

}
