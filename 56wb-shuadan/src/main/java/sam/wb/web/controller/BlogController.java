package sam.wb.web.controller;

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
import sam.wb.db.repository.BlogArticleRepository;

@Controller
@RequestMapping(value="/blog")
public class BlogController extends SuperController{

	private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
	 
	@Autowired
	BlogArticleRepository blogArticleRepository;
	
	@ExceptionHandler
	public @ResponseBody JsonMsg handleException(HttpServletRequest request, Exception ex){
	   
		logger.error("handleException", ex);
		return new JsonMsg(false,ex.getMessage()) ;
	}
	@RequestMapping("/{userName}/articles")
	public  String index(Map<String, Object> model,@PathVariable String userName,
			@RequestParam(value="page",required=false,defaultValue="0")  int page,
			@RequestParam(value="limit",required=false,defaultValue="20")  int limit) {
		
		Page<Article> articlePage= blogArticleRepository.findByCreateUserOrderByIdDesc(userName,new PageRequest(page, limit));
		
		model.put("list", articlePage.getContent());
		model.put("rowNum", articlePage.getTotalElements());
		model.put("pageNum", articlePage.getTotalPages());
		model.put("page", page);
		model.put("limit", limit);
		
		return "blog/index";
	}
	@RequestMapping("/{title}-{id}.html")
	public String artice_detail(Map<String, Object> model,@PathVariable String title,@PathVariable Long id){
		
		Article oneArticle= blogArticleRepository.findOne(id);
		
		model.put("oneArticle", oneArticle);
		
		return "blog/detail";
	}
	
	@RequestMapping(value="/edit.html",method = RequestMethod.GET)
	public  String edit(Map<String, Object> model) {
		
		navs.put("博客", "/list.html");		
		navs.put("编辑", "");		
		model.put("navs", navs);

		model.put("navs", navs);
		
		model.put("time", new Date());
		model.put("message", "hello the world");
		return "blog/edit";
	}
	
	@RequestMapping(value="/list.html")
	public  String list(Map<String, Object> model,
			@RequestParam(value="page",required=false,defaultValue="0")  int page,
			@RequestParam(value="limit",required=false,defaultValue="20")  int limit) {
		
		if(App.getCurUser()==null){
			
			//登录
			//return "forward:/hello" => 转发到能够匹配 /hello 的 controller 上  
			return "redirect:/login.html";
		}
		
		String userName=App.getCurUser().getUsername();
		
		Page<Article> articlePage= blogArticleRepository.findByCreateUserOrderByIdDesc(userName,new PageRequest(page, limit));
		
		model.put("list", articlePage.getContent());
		model.put("rowNum", articlePage.getTotalElements());
		model.put("pageNum", articlePage.getTotalPages());
		model.put("page", page);
		model.put("limit", limit);
		
		return "blog/list";
	}
	
	@RequestMapping(value="/submit-blog-edit.action",method = RequestMethod.POST)
	public @ResponseBody JsonMsg submit_edit(@ModelAttribute Article m) {
		
		JsonMsg result=null;
		
		if(App.getCurUser()==null){
			
			return new JsonMsg(false,"请登录系统,连接已经超时！");
		}
		
		try{
			
			if(m.getId()!=null){

				Article existOne= blogArticleRepository.findOne(m.getId());
				if(existOne!=null){
					
					existOne.setUpdate_date(new Date());
					existOne.setTitle(m.getTitle());
					existOne.setContent(m.getContent());
					
					blogArticleRepository.save(existOne);
					
					result=new JsonMsg(true,"保存成功");
					
				}else{
					
					result=new JsonMsg(false,"保存失败，该文章没有找到！");
				}
			}else{
				
				m.setCreate_date(new Date());
				m.setUpdate_date(new Date());
				m.setStatus(0);
				m.setView_num(0);
				m.setCreateUser(App.getCurUser().getUsername());
				
				blogArticleRepository.save(m);
				
				result=new JsonMsg(true,"保存成功");
			}
			
		}catch(Exception ex){
			logger.error("ex", ex);
			
			result=new JsonMsg(false,"系统故障，请稍后重试！");
		}
		return result;
	}
	
	
}
