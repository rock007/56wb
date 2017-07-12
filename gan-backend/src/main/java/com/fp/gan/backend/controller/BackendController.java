package com.fp.gan.backend.controller;

import com.fp.gan.core.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;


@Controller
public class BackendController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(BackendController.class);

	@RequestMapping("/")
	public String backend(Map<String, Object> model){

		return "index";
	}

	@RequestMapping("/doashboard")
	public String dashboard(Map<String, Object> model){

		return "doashboard";
	}


	@RequestMapping("/crud.html")
	public String crud(Map<String, Object> model){

		return "crud";
	}

}
