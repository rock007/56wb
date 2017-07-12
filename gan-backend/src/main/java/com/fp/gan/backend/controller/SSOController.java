package com.fp.gan.backend.controller;


import com.fp.gan.core.base.BaseController;
import com.fp.gan.core.model.JsonResult;
import com.fp.gan.core.shiro.session.SystemSession;
import com.fp.gan.core.shiro.session.SystemSessionDao;
import com.fp.gan.core.utils.RedisUtil;
import com.fp.gan.db.entity.sys.User;
import com.fp.gan.db.service.SysUserService;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;

/**
 * 单点登录管理
 * sam is here  2016/12/10.
 */
@Controller
@RequestMapping("/sso")
public class SSOController extends BaseController {

    private final static Logger _log = LoggerFactory.getLogger(SSOController.class);
    // 全局会话key
    private final static String SERVER_SESSION_ID = "server-session-id";
    // 全局会话key列表
    private final static String SERVER_SESSION_IDS = "server-session-ids";
    // code key
    private final static String SERVER_CODE = "server-code";

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SystemSessionDao systemSessionDao;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String serverSessionId = session.getId().toString();
        // 判断是否已登录，如果已登录，则回跳
        String code =""; //RedisUtil.get(ZHENG_Sys_SERVER_SESSION_ID + "_" + serverSessionId);
        // code校验值
        if (StringUtils.isNotBlank(code)) {
            // 回跳
            String backurl = request.getParameter("backurl");
            String username = (String) subject.getPrincipal();
            if (StringUtils.isBlank(backurl)) {
                backurl = "/";
            } else {
                if (backurl.contains("?")) {
                    backurl += "&Sys_code=" + code + "&Sys_username=" + username;
                } else {
                    backurl += "?Sys_code=" + code + "&Sys_username=" + username;
                }
            }
            _log.debug("认证中心帐号通过，带code回跳：{}", backurl);
            return "redirect:" + backurl;
        }
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response,
                        Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        if (StringUtils.isBlank(username)) {
            model.addAttribute("msg","帐号不能为空！");
            return "login";
        }
        if (StringUtils.isBlank(password)) {
            model.addAttribute("msg","密码不能为空！");
            return "login";
        }
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        // 判断是否已登录，如果已登录，则回跳，防止重复登录
        String hasCode = "";//RedisUtil.get(ZHENG_Sys_SERVER_SESSION_ID + "_" + sessionId);
        // code校验值
        if (StringUtils.isBlank(hasCode)) {

            //check
            User oneUser= sysUserService.findUserByName(username);

            if(oneUser==null){

                model.addAttribute("msg","用户不存在！");
                return "login";

            }
            if(!oneUser.getPassword().equals(password)){

                model.addAttribute("msg","用户密码不正确！");
                return "login";

            }

            // 使用shiro认证
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            try {
                if (BooleanUtils.toBoolean(rememberMe)) {
                    usernamePasswordToken.setRememberMe(true);
                } else {
                    usernamePasswordToken.setRememberMe(false);
                }
                subject.login(usernamePasswordToken);

            } catch (UnknownAccountException e) {
                model.addAttribute("msg","帐号不存在！");
                return "login";
            } catch (IncorrectCredentialsException e) {

                model.addAttribute("msg","密码错误！");
                return "login";
            } catch (LockedAccountException e) {
                model.addAttribute("msg","帐号已锁定！");
                return "login";
            } catch (AuthenticationException e) {
                model.addAttribute("msg","登陆失败！");
                return "login";
            }
            // 更新session状态
            systemSessionDao.updateStatus(sessionId, SystemSession.OnlineStatus.on_line);
            // 全局会话sessionId列表，供会话管理
            RedisUtil.lpush(SERVER_SESSION_IDS, sessionId.toString());
            // 默认验证帐号密码正确，创建code
            String code = UUID.randomUUID().toString();
            // 全局会话的code
            RedisUtil.set(SERVER_SESSION_ID + "_" + sessionId, code, (int) subject.getSession().getTimeout() / 1000);
            // code校验值
            RedisUtil.set(SERVER_CODE + "_" + code, code, (int) subject.getSession().getTimeout() / 1000);
        }
        // 回跳登录前地址
        String backurl = request.getParameter("backurl");
        if (StringUtils.isBlank(backurl)) {
            return "redirect:/";
        } else {

            //return new JsonResult(1, backurl);
            model.addAttribute("msg",backurl);
            return "login";
        }
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        // shiro退出登录
        SecurityUtils.getSubject().logout();
        // 跳回原地址
        String redirectUrl = request.getHeader("Referer");
        if (null == redirectUrl) {
            redirectUrl = "/";
        }
        return "redirect:" + redirectUrl;
    }

    @RequestMapping("/unauthorized")
    public String unauthorized(Map<String, Object> model){

        return "unauthorized";
    }

}