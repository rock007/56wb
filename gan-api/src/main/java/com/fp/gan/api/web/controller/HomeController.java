package com.fp.gan.api.web.controller;

import java.util.Date;
import java.util.Map;

import com.fp.gan.core.model.JsonMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import reactor.bus.Event;
import reactor.bus.EventBus;


@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	 
	@Autowired
	EventBus eventBus;
	
	@RequestMapping("/")
	public  String index(Map<String, Object> model) {

		return "index";
	}

	@RequestMapping("/notify.json")
	public @ResponseBody JsonMsg doBusNotify(String msg){
		
		eventBus.notify("quotes", Event.wrap(msg));
		
		return new JsonMsg(true,"测试数据"+msg);
	}

}
