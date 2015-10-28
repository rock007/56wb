package sam.wb.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import sam.wb.db.service.imp.AccountAuthService;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccountAuthService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
    	http.headers().frameOptions().disable();
    	
		http.authorizeRequests()
                .antMatchers( "/public/**").permitAll()
                .antMatchers( "/","/static/**").permitAll()
                .antMatchers("/plugins/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/blog/**/articles**").permitAll()                
                .antMatchers("/","**.json").permitAll()                
                .anyRequest().fullyAuthenticated()
                .and()
                	.formLogin()         
                	.loginPage("/login.html")
                	.failureUrl("/login.html?error")
                	.successHandler(new AuthSuccessHandler())
                	.permitAll()
                .and()
                	.logout()
                	.logoutUrl("/logout")             
                	.logoutSuccessUrl("/login.html")
                	.permitAll()
                .and()
                	.exceptionHandling().accessDeniedPage("/login.html?error")
				.and()
					.csrf().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService);
    }

}