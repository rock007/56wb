package com.fp.gan.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class GanApiApplication {

    public static void main(String[] args) {
    	
        SpringApplication.run(GanApiApplication.class, args);
    }

}
