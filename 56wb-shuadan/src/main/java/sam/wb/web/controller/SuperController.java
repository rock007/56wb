package sam.wb.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


public class SuperController {

	private static final Logger logger = LoggerFactory.getLogger(SuperController.class);
	
	protected Map<String,String> navs =new HashMap<String,String>();
	
}
