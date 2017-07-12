package com.fp.gan.system.controller.manage;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.fp.gan.core.validator.LengthValidator;
import com.fp.gan.system.comm.constant.Result;
import com.fp.gan.system.comm.constant.ResultConstant;
import com.fp.gan.system.controller.BaseController;
import com.fp.gan.system.dao.sys.model.SysRole;
import com.fp.gan.system.dao.sys.model.SysRoleExample;
import com.fp.gan.system.dao.sys.service.SysRolePermissionService;
import com.fp.gan.system.dao.sys.service.SysRoleService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色controller
 * sam is here  2017/2/6.
 */
@Controller
@RequestMapping("/manage/role")
public class SysRoleController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    @RequiresPermissions("Sys:role:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/role/index";
    }


    @RequiresPermissions("Sys:role:permission")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.GET)
    public String permission(@PathVariable("id") int id, ModelMap modelMap) {
        SysRole role = sysRoleService.selectByPrimaryKey(id);
        modelMap.put("role", role);
        return "/manage/role/permission";
    }

    @RequiresPermissions("Sys:role:permission")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object permission(@PathVariable("id") int id, HttpServletRequest request) {
        JSONArray datas = JSONArray.parseArray(request.getParameter("datas"));
        int result = sysRolePermissionService.rolePermission(datas, id);
        return new Result(ResultConstant.SUCCESS, result);
    }

    @RequiresPermissions("Sys:role:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        SysRoleExample SysRoleExample = new SysRoleExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            SysRoleExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            SysRoleExample.or()
                    .andTitleLike("%" + search + "%");
        }
        List<SysRole> rows = sysRoleService.selectByExampleForOffsetPage(SysRoleExample, offset, limit);
        long total = sysRoleService.countByExample(SysRoleExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @RequiresPermissions("Sys:role:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/role/create";
    }

    @RequiresPermissions("Sys:role:create")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(SysRole SysRole) {
        ComplexResult result = FluentValidator.checkAll()
                .on(SysRole.getName(), new LengthValidator(1, 20, "名称"))
                .on(SysRole.getTitle(), new LengthValidator(1, 20, "标题"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new Result(ResultConstant.INVALID_LENGTH, result.getErrors());
        }
        long time = System.currentTimeMillis();
        SysRole.setCtime(time);
        SysRole.setOrders(time);
        int count = sysRoleService.insertSelective(SysRole);
        return new Result(ResultConstant.SUCCESS, count);
    }

    @RequiresPermissions("Sys:role:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = sysRoleService.deleteByPrimaryKeys(ids);
        return new Result(ResultConstant.SUCCESS, count);
    }

    @RequiresPermissions("Sys:role:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        SysRole role = sysRoleService.selectByPrimaryKey(id);
        modelMap.put("role", role);
        return "/manage/role/update";
    }

    @RequiresPermissions("Sys:role:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") int id, SysRole SysRole) {
        ComplexResult result = FluentValidator.checkAll()
                .on(SysRole.getName(), new LengthValidator(1, 20, "名称"))
                .on(SysRole.getTitle(), new LengthValidator(1, 20, "标题"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new Result(ResultConstant.INVALID_LENGTH, result.getErrors());
        }
        SysRole.setRoleId(id);
        int count = sysRoleService.updateByPrimaryKeySelective(SysRole);
        return new Result(ResultConstant.SUCCESS, count);
    }

}
