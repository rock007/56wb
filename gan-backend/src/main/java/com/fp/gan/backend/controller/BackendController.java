package com.fp.gan.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;


@Controller
//@RequestMapping(value="/")
public class BackendController extends SuperController{

	private static final Logger logger = LoggerFactory.getLogger(BackendController.class);
	
	@RequestMapping("/doashboard")
	public String dashboard(Map<String, Object> model){
		
		navs.put("首页", "/");
		navs.put("后台", "");
		
		model.put("navs", navs);
		return "doashboard";
	}

	@RequestMapping("/")
	public String backend(Map<String, Object> model){

		return "index";
	}

	@RequestMapping("/crud.html")
	public String crud(Map<String, Object> model){

		return "crud";
	}

}
