package com.fp.gan.api.app.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fp.gan.db.entity.sys.User;
import com.fp.gan.db.repository.sys.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component("accountAuthService")
public class AccountAuthService implements UserDetailsService{

    private static final Logger loggger = LoggerFactory.getLogger(AccountAuthService.class);
    
    @Autowired
    private UserRepository userRepository;
    
	@Override
	public UserDetails loadUserByUsername(String name)
			throws UsernameNotFoundException {
		
		final User oneUser= userRepository.findByUsername(name);

		if(oneUser==null){
			
			loggger.warn("用户不存在！");
			throw new UsernameNotFoundException("用户没有找到！");
		}
		
	    return new UserDetails() {
	      
	      private static final long serialVersionUID = 2059202961588104658L;

	      @Override
	      public boolean isEnabled() {
	        return true;
	      }
	      
	      @Override
	      public boolean isCredentialsNonExpired() {
	        return true;
	      }
	      
	      @Override
	      public boolean isAccountNonLocked() {
	        return true;
	      }
	      
	      @Override
	      public boolean isAccountNonExpired() {
	        return true;
	      }
	      
	      @Override
	      public String getUsername() {
	        return oneUser.getUsername();
	      }
	      
	      @Override
	      public String getPassword() {
	       
	    	  return oneUser.getPassword();
	      }
	      
	      @Override
	  	public Collection<? extends GrantedAuthority> getAuthorities() {
	  		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
	  		/***
	  		for ( Role role : oneUser.getRoles() ){
	  			SimpleGrantedAuthority auth = new SimpleGrantedAuthority( role.getAuthority() );
	  			authList.add(auth);
	  		}
	  		***/
	  		return authList;
	  	}
	      
	    };
	}
	
}
