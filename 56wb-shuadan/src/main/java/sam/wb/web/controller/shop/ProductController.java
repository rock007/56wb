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
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	 
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductImageRepository productImageRepository;
	
	@ExceptionHandler
	public @ResponseBody JsonMsg handleException(HttpServletRequest request, Exception ex){
	   
		logger.error("handleException", ex);
		return new JsonMsg(false,ex.getMessage()) ;
	}
	
	@RequestMapping(value="/post-product.html",method = RequestMethod.GET)
	public  String edit(Map<String, Object> model) {
		
		model.put("time", new Date());
		model.put("message", "hello the world");
		return "shop/edit";
	}
	
	@RequestMapping(value="/submit-product-edit.action",method = RequestMethod.POST)
	public @ResponseBody JsonMsg submit_edit(@ModelAttribute Product m) {
		
		JsonMsg result=null;
		
		if(App.getCurUser()==null){
			
			return new JsonMsg(false,"请登录系统,连接已经超时！");
		}
		
		try{
			
			if(m.getId()!=null){

				Product existOne= productRepository.findOne(m.getId());
				if(existOne!=null){
					
					existOne.setUpdate_date(new Date());
					existOne.setTitle(m.getTitle());
					
					productRepository.save(existOne);
					
					result=new JsonMsg(true,"保存成功");
					
				}else{
					
					result=new JsonMsg(false,"保存失败，该文章没有找到！");
				}
			}else{
				
				m.setCreate_date(new Date());
				m.setUpdate_date(new Date());
				m.setStatus(0);
				
				m.setCreateUser(App.getCurUser().getUsername());
				
				productRepository.save(m);
				
				result=new JsonMsg(true,"保存成功");
			}
			
		}catch(Exception ex){
			logger.error("ex", ex);
			
			result=new JsonMsg(false,"系统故障，请稍后重试！");
		}
		return result;
	}
	
	
	@RequestMapping(value="/submit-product-image.action",method = RequestMethod.POST)
	public @ResponseBody JsonMsg submit_product_image(@ModelAttribute Product m) {
		
		JsonMsg result=null;
		
		if(App.getCurUser()==null){
			
			return new JsonMsg(false,"请登录系统,连接已经超时！");
		}
		
		try{
			
			if(m.getId()!=null){

				Product existOne= productRepository.findOne(m.getId());
				if(existOne!=null){
					
					existOne.setUpdate_date(new Date());
					existOne.setTitle(m.getTitle());
					
					productRepository.save(existOne);
					
					result=new JsonMsg(true,"保存成功");
					
				}else{
					
					result=new JsonMsg(false,"保存失败，该文章没有找到！");
				}
			}else{
				
				m.setCreate_date(new Date());
				m.setUpdate_date(new Date());
				m.setStatus(0);
				
				m.setCreateUser(App.getCurUser().getUsername());
				
				productRepository.save(m);
				
				result=new JsonMsg(true,"保存成功");
			}
			
		}catch(Exception ex){
			logger.error("ex", ex);
			
			result=new JsonMsg(false,"系统故障，请稍后重试！");
		}
		return result;
	}
	
}
