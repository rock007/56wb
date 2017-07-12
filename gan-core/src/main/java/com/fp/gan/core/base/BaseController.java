package com.fp.gan.core.base;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.InvalidSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sam on 2017/7/7.
 */
public abstract class BaseController {

    private final static Logger _log = LoggerFactory.getLogger(BaseController.class);

    /**
     * 统一异常处理
     * @param request
     * @param response
     * @param exception
     */
    @ExceptionHandler
    public String exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        _log.error("统一异常处理：", exception);
        request.setAttribute("ex", exception);
        if (null != request.getHeader("X-Requested-With") && request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {
            request.setAttribute("requestHeader", "ajax");
        }

        // shiro没有权限异常
        if (exception instanceof UnauthorizedException
                ||exception instanceof UnauthenticatedException) {
            return "403";
        }
        // shiro会话已过期异常
        if (exception instanceof InvalidSessionException) {
            return "error";
        }
        return "error";
    }

    /***
     * not work
     * @param model
     */
    @ModelAttribute
    public void populateModel(Model model) {
        model.addAttribute("host", "http://127.0.0.1:1002");
    }
}
