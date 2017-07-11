package com.fp.gan.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages ={ "com.fp.gan.backend"
        ,"com.fp.gan.db.service"})
@EnableJpaRepositories(basePackages ={ "com.fp.gan.db.repository"})
@EntityScan(basePackages ={ "com.fp.gan.db.entity"})
@EnableAutoConfiguration
public class BackendApplication {

    public static void main(String[] args) {
    	
        SpringApplication.run(BackendApplication.class, args);
    }

}