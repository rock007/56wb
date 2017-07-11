package com.fp.gan.system.Interceptor;

import com.fp.gan.system.dao.sys.model.SysUser;
import com.fp.gan.system.dao.sys.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台过滤器
 */
public class ManageInterceptor extends HandlerInterceptorAdapter {

	private static Logger _log = LoggerFactory.getLogger(ManageInterceptor.class);

	private static final String ZHENG_OSS_ALIYUN_OSS_POLICY = "dddd";

	@Autowired
	SysUserService sysUserService;


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		//request.setAttribute("ZHENG_OSS_ALIYUN_OSS_POLICY", ZHENG_OSS_ALIYUN_OSS_POLICY);
		// 过滤ajax
		if (null != request.getHeader("X-Requested-With") && request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {
			return true;
		}
		// 登录信息
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		SysUser curUser = sysUserService.selectUserByUsername(username);
		request.setAttribute("curUser", curUser);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		super.afterConcurrentHandlingStarted(request, response, handler);
	}
}
