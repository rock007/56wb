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
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@RequestMapping("/")
	public  String index(Map<String, Object> model) {
		
		model.put("time", new Date());
		model.put("message", "hello the world");
		return "index";
	}
	@RequestMapping("/login.html")
	public String login(){
		
		return "login";
	}
	
	@RequestMapping("/about-us.html")
	public String about_us(){
		
		return "about-me";
	}
	
	@RequestMapping("/notify.json")
	public @ResponseBody JsonMsg doBusNotify(String msg){
		
		eventBus.notify("quotes", Event.wrap(msg));
		
		return new JsonMsg(true,"测试数据"+msg);
	}
	
	@RequestMapping("/send-msgq.html")
	public @ResponseBody String sendMsgQ(String mm){
		
		rabbitTemplate.convertAndSend(AmqpConfig.queueName, "Hello from RabbitMQ!"+mm);
		return "ok";
	}
	
	@RequestMapping("/rec-msgq.html")
	public @ResponseBody String recMsgQ(String mm){
		
		return "ok";
	}
	
	@RequestMapping("/demo.html")
    public  String demo() {
         
		return "demo";
    }
	
	@RequestMapping("/thing")
    public @ResponseBody MyThing thing() {
            return new MyThing();
    }

	
	public class MyThing{
		
		private int id;
		
		private String name;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}
}
