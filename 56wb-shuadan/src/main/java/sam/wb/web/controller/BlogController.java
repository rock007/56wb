package sam.wb.web.controller;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import reactor.bus.EventBus;
import sam.wb.db.repository.BlogArticleRepository;

@Controller
@RequestMapping(value="/blog")
public class BlogController {

	private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
	 
	@Autowired
	BlogArticleRepository blogArticleRepository;
	
	@RequestMapping("/index.html")
	public  String index(Map<String, Object> model) {
		
		model.put("time", new Date());
		model.put("message", "hello the world");
		return "blog/index";
	}
	
	@RequestMapping(value="/edit.html",method = RequestMethod.GET)
	public  String edit(Map<String, Object> model) {
		
		model.put("time", new Date());
		model.put("message", "hello the world");
		return "blog/edit";
	}
	
	@RequestMapping(value="/edit.html",method = RequestMethod.POST)
	public  String submit_edit(Map<String, Object> model) {
		
		model.put("time", new Date());
		model.put("message", "hello the world");
		
		return "blog/index";
	}
	
	
}
