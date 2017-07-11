package com.fp.gan.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 后台controller
 */
@Controller
@RequestMapping("/manage")
public class ManageController  extends BaseController{

	private static Logger _log = LoggerFactory.getLogger(ManageController.class);

	@RequestMapping(value = "/")
	public String Home() {
		return "index";
	}
	/**
	 * 后台首页
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/index";
	}

}