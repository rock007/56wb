package com.fp.gan.api.web.controller.shop;

import javax.servlet.http.HttpServletRequest;

import com.fp.gan.core.model.JsonMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fp.gan.db.repository.shop.ProductImageRepository;
import com.fp.gan.db.repository.shop.ProductRepository;

@Controller
@RequestMapping(value="/shop")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	 
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductImageRepository productImageRepository;
	
	@ExceptionHandler
	public @ResponseBody
	JsonMsg handleException(HttpServletRequest request, Exception ex){
	   
		logger.error("handleException", ex);
		return new JsonMsg(false,ex.getMessage()) ;
	}
	
	@RequestMapping("/orders-list.html")
	public String product_list(){
		
		return "shop/orders-list";
	}
	
}
