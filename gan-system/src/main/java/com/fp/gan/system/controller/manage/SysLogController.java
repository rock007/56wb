package com.fp.gan.system.controller.manage;

import com.fp.gan.system.comm.constant.Result;
import com.fp.gan.system.comm.constant.ResultConstant;
import com.fp.gan.system.controller.BaseController;
import com.fp.gan.system.dao.sys.model.SysLog;
import com.fp.gan.system.dao.sys.model.SysLogExample;
import com.fp.gan.system.dao.sys.service.SysLogService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日志controller
 * sam is here  2017/3/14.
 */
@Controller
@RequestMapping("/manage/log")
public class SysLogController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(SysLogController.class);

    @Autowired
    private SysLogService sysLogService;

    @RequiresPermissions("Sys:log:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/log/index";
    }

    @RequiresPermissions("Sys:log:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        SysLogExample sysLogExample = new SysLogExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            sysLogExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            sysLogExample.or()
                    .andDescriptionLike("%" + search + "%");
        }
        List<SysLog> rows = sysLogService.selectByExampleForOffsetPage(sysLogExample, offset, limit);
        long total = sysLogService.countByExample(sysLogExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @RequiresPermissions("Sys:log:delete")
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = sysLogService.deleteByPrimaryKeys(ids);
        return new Result(ResultConstant.SUCCESS, count);
    }

}