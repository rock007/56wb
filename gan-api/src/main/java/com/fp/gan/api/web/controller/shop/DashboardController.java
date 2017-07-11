package com.fp.gan.api.web.controller.shop;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fp.gan.core.model.JsonMsg;

@Controller
@RequestMapping(value="/shop")
public class DashboardController {

	private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);
	
	@ExceptionHandler
	public @ResponseBody JsonMsg handleException(HttpServletRequest request, Exception ex){
	   
		logger.error("handleException", ex);
		return new JsonMsg(false,ex.getMessage()) ;
	}
	
	@RequestMapping("/index.html")
	public String index(){
		
		return "shop/index";
	}
	
	@RequestMapping("/manager.html")
	public String manager(){
		
		return "shop/manager";
	}
	
}
