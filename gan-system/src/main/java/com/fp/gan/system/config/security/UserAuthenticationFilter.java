package com.fp.gan.system.config.security;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fp.gan.core.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;


/**
 * 验证用户是否在线 用户已验证通过，并且在全局在线用户列表,否定登陆超时
 * 
 * @author LiuJincheng
 * @version 1.0
 */
public class UserAuthenticationFilter extends
		org.apache.shiro.web.filter.authc.UserFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		System.out.println("验证登陆信息==" + req.getRequestURI());
		System.out.println("sessionId==" + req.getSession().getId());
		
		String curUrl = getRequestUrl(request);
		Subject curSubject = SecurityUtils.getSubject();
		if(//curSubject.getPrincipal() == null||
		 StringUtils.endsWithAny(curUrl, ".js",".css",".html",".map")
		|| StringUtils.endsWithAny(curUrl, ".jpg",".png",".gif", ".jpeg",".ico")
		|| StringUtils.equals(curUrl, "/unauthor")
				|| curUrl.indexOf("/sso/")!=-1) {
			return true;
		}

		String tokenId = StringUtil.getNoneNullString(req
				.getParameter("tokenId"));
		req.setAttribute("ajax", req.getParameter("ajax"));
		if (tokenId.length() > 0) {
			req.getSession().setAttribute("tokenId", tokenId);
		}

		if (isLoginRequest(request, response)) {
			return true;
		} else {

			Subject subject = getSubject(request, response);
			System.out.println("subject url==" + req.getRequestURI()
					+ "  flag=" + req.getRequestURI().indexOf("interface/"));
			// 验证是否有登陆用户
			// 过滤掉不需要登录的请求
			String uri = req.getRequestURI();
			if (uri.indexOf("interface/") > 0 || uri.indexOf("/news") > 0
					|| uri.indexOf("/affiche") > 0
					|| uri.indexOf("/msgwarn") > 0 || uri.indexOf("/gwgl") > 0
					|| uri.indexOf("/gwcs") > 0 || uri.indexOf("/scxd") > 0
					|| uri.indexOf("/gwk") > 0 || uri.indexOf("/remark") > 0
					|| uri.indexOf("/dept/subList.do") > 0
					|| uri.indexOf("/user/deptUsers.do") > 0
					|| uri.indexOf("/user/info.do") > 0
					|| uri.indexOf("/home/") > 0
					|| uri.indexOf("/affiche/queryNewsAndDaiban.do") > 0) {
				// res.setHeader("Access-Control-Allow-Origin", "*");
				// tradCode.put(tradeCode, value)
				// if(tradCode.get(tradeCode))
				return true;
			}
			if (subject.getPrincipal() != null && subject.isAuthenticated()) {
				// 判断用户是否在全局在线列表
				String userId = (String) subject.getPrincipal();// 获取用户id
				/*****=====================================!!!!
				Map<String, OnLineUser> usersMap = ServletUtil.getOnLineUsers();
				OnLineUser om = usersMap.get(userId);// 获取在线人员列表
				// 判断当前用户是否在在线人员列表里面，如果不在，不能继续访问
				if (om == null) {
					return false;
				} else {
					Map<String, LoginInfo> loginInfos = om.getLoginInfos();
					if (loginInfos.containsKey(((HttpServletRequest) request)
							.getSession().getId())) {
						return true;
					} else {
						return false;
					}
				}
				***/
				return true;
			} else {

				return false;
			}
		}
	}
	
	/***
	 * get full path
	 * @param request
	 * @return
	 */
	private String getRequestUrl(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest)request;
		String queryString = req.getQueryString();
		queryString = StringUtils.isBlank(queryString)?"": "?"+queryString;
		return req.getRequestURI()+queryString;
	}
	

}
