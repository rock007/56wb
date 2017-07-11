package com.fp.gan.system;

import com.fp.gan.system.config.ApplicationRefreshEventListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages ={ "com.fp.gan.system"})
@MapperScan("com.fp.gan.system.dao.sys.mapper")
@EnableAutoConfiguration
public class SystemApplication {

    public static void main(String[] args) {
    	
        //SpringApplication.run(SystemApplication.class, args);

        SpringApplication app = new SpringApplication(SystemApplication.class);
        app.addListeners(new ApplicationRefreshEventListener());
        app.run(args);
    }

}
