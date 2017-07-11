package com.fp.gan.system.comm.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Created by sam on 2017/7/10.
 */
@WebFilter(
        filterName = "shiroFilter",
        urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "targetFilterLifecycle", value = "true")
        }
)
public class ShiroFilter extends org.springframework.web.filter.DelegatingFilterProxy {

}