package sam.wb.web.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value="/backend")
public class BackendController extends SuperController{

	private static final Logger logger = LoggerFactory.getLogger(BackendController.class);
	
	@RequestMapping("/")
	public String backend(Map<String, Object> model){
		
		navs.put("首页", "/");
		navs.put("后台", "");
		
		model.put("navs", navs);
		return "backend/manager";
	}
	
}
