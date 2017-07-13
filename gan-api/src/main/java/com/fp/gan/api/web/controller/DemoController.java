package com.fp.gan.api.web.controller;

import java.util.Map;

import com.fp.gan.core.model.JsonMsg;
import com.fp.gan.core.model.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.bus.Event;


@Controller
@RequestMapping(value="/api")
public class DemoController extends JsonBaseController{

	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
	
	@RequestMapping("/demo.action")
	public @ResponseBody JsonResult demo(Map<String, Object> model){
		

		return new JsonResult(1,"this is a msg");
	}

}
