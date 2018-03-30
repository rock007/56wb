package sam.wb.web.controller.shop;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import reactor.bus.EventBus;
import sam.wb.app.App;
import sam.wb.core.model.JsonMsg;
import sam.wb.db.entity.blog.Article;
import sam.wb.db.entity.shop.Product;
import sam.wb.db.repository.BlogArticleRepository;
import sam.wb.db.repository.shop.ProductImageRepository;
import sam.wb.db.repository.shop.ProductRepository;

@Controller
@RequestMapping(value="/shop")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	 
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductImageRepository productImageRepository;
	
	@ExceptionHandler
	public @ResponseBody JsonMsg handleException(HttpServletRequest request, Exception ex){
	   
		logger.error("handleException", ex);
		return new JsonMsg(false,ex.getMessage()) ;
	}
	
	@RequestMapping("/orders-list.html")
	public String product_list(){
		
		return "shop/orders-list";
	}
	
}
