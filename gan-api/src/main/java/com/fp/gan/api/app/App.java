package com.fp.gan.api.app;

import java.text.SimpleDateFormat;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class App {
	
	public static String CON_SESSION_LOGIN="GAN_API_SESSION";
	
	
	private static SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");

	public static String basePath="/"; 
	
	public static String GetCurDT(){	            
          return sd.format(new java.util.Date());
	}
	
	public static UserDetails getCurUser(){

		UserDetails curUser=null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			curUser = (UserDetails)principal;
		}
		return curUser;
	}
	
}
