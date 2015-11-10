package sam.wb.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value="/backend")
public class BackendController {

	private static final Logger logger = LoggerFactory.getLogger(BackendController.class);
	
	@RequestMapping("/")
	public String backend(){
		
		return "backend/manager";
	}
	
}
