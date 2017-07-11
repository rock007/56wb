package com.fp.gan.backend.config;

import org.apache.shiro.web.servlet.ShiroFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ImportResource({"config/security/applicationContext-shiro.xml"})
public class Config {

	
}
