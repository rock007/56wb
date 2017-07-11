package com.fp.gan.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages ={ "com.fp.gan.db.service"
        ,"com.fp.gan.home"})
@EnableJpaRepositories(basePackages ={"com.fp.gan.db"})
@EntityScan(basePackages ={ "com.fp.gan.db"})
public class HomeApplication {

    public static void main(String[] args) {
    	
        SpringApplication.run(HomeApplication.class, args);
    }

}
