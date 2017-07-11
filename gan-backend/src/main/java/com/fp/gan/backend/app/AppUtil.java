package com.fp.gan.backend.app;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.text.SimpleDateFormat;

public class AppUtil {
	
	public static String CON_SESSION_LOGIN="BACKEND_SESSION";
	
	
	private static SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");

	public static String basePath="/"; 
	
	public static String GetCurDT(){	            
          return sd.format(new java.util.Date());
	}

	/**
	public static UserDetails getCurUser(){

		Subject currentUser = SecurityUtils.getSubject();

		currentUser.getPrincipal();

		return null;
	}
	***/
	
}
