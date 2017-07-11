package com.fp.gan.backend.controller;

import com.fp.gan.backend.app.AppUtil;
import com.fp.gan.core.model.JsonMsg;
import com.fp.gan.db.entity.blog.Article;
import com.fp.gan.db.repository.BlogArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping(value="/blog")
public class BlogController extends SuperController{

	private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
	 
	@Autowired
	BlogArticleRepository blogArticleRepository;
	
	@ExceptionHandler
	public @ResponseBody
	JsonMsg handleException(HttpServletRequest request, Exception ex){
	   
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

		
		String userName="";//AppUtil.getCurUser().getUsername();
		
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
				m.setCreateUser("");
				
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
