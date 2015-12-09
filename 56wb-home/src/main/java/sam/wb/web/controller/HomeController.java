package sam.wb.web.controller;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import reactor.bus.Event;
import reactor.bus.EventBus;
import sam.wb.app.msg.config.AmqpConfig;
import sam.wb.core.model.JsonMsg;


@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	 
	@Autowired
	EventBus eventBus;
	
	@RequestMapping("/")
	public  String index(Map<String, Object> model) {
		
		model.put("menu", "home");
		model.put("message", "hello the world");
		return "Home/Index";
	}
	
	@RequestMapping("/web-make")
	public String WebSite(Map<String, Object> model){
		
		model.put("menu", "web-make");
		return "WebSite/Index";
	}
	
	@RequestMapping("/web-make/cases")
	public String WebSite_case(Map<String, Object> model){
		
		model.put("menu", "web-make");
		model.put("sub-menu", "cases");
		return "WebSite/Cases";
	}
	
	@RequestMapping("/web-make/pay")
	public String WebSite_pay(Map<String, Object> model){
		
		model.put("menu", "web-make");
		model.put("sub-menu", "pay");
		return "WebSite/Pay";
	}
	
	@RequestMapping("/app-dev")
	public String appDev(Map<String, Object> model){
		
		model.put("menu", "app-dev");
		return "AppDev/Index";
	}
	
	@RequestMapping("/about-us.html")
	public String about_us(Map<String, Object> model){
		
		model.put("menu", "about-us");
		return "Home/Contact";
	}
	
	@RequestMapping("/login.html")
	public String login(){
		
		return "Account/Login";
	}
	
	@RequestMapping("/notify.json")
	public @ResponseBody JsonMsg doBusNotify(String msg){
		
		eventBus.notify("quotes", Event.wrap(msg));
		
		return new JsonMsg(true,"测试数据"+msg);
	}
	
}
