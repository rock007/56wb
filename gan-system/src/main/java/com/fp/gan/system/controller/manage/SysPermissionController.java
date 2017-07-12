package com.fp.gan.system.controller.manage;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.fp.gan.core.validator.LengthValidator;
import com.fp.gan.system.comm.constant.Result;
import com.fp.gan.system.comm.constant.ResultConstant;
import com.fp.gan.system.controller.BaseController;
import com.fp.gan.system.dao.sys.model.SysPermission;
import com.fp.gan.system.dao.sys.model.SysPermissionExample;
import com.fp.gan.system.dao.sys.model.SysSystem;
import com.fp.gan.system.dao.sys.model.SysSystemExample;
import com.fp.gan.system.dao.sys.service.SysApiService;
import com.fp.gan.system.dao.sys.service.SysPermissionService;
import com.fp.gan.system.dao.sys.service.SysSystemService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限controller
 * sam is here  2017/2/6.
 */
@Controller
@RequestMapping("/manage/permission")
public class SysPermissionController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(SysPermissionController.class);

    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private SysSystemService sysSystemService;

    @Autowired
    private SysApiService sysApiService;

    @RequiresPermissions("Sys:permission:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/permission/index";
    }

    @RequiresPermissions("Sys:permission:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, defaultValue = "0", value = "type") int type,
            @RequestParam(required = false, defaultValue = "0", value = "systemId") int systemId,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        SysPermissionExample sysPermissionExample = new SysPermissionExample();
        SysPermissionExample.Criteria criteria = sysPermissionExample.createCriteria();
        if (0 != type) {
            criteria.andTypeEqualTo((byte) type);
        }
        if (0 != systemId) {
            criteria.andSystemIdEqualTo(systemId);
        }
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            sysPermissionExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            sysPermissionExample.or()
                    .andNameLike("%" + search + "%");
        }
        List<SysPermission> rows = sysPermissionService.selectByExampleForOffsetPage(sysPermissionExample, offset, limit);
        long total = sysPermissionService.countByExample(sysPermissionExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @RequiresPermissions("Sys:permission:read")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object role(@PathVariable("id") int id) {
        return sysPermissionService.getTreeByRoleId(id);
    }

    @RequiresPermissions("Sys:permission:read")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object user(@PathVariable("id") int id, HttpServletRequest request) {
        return sysPermissionService.getTreeByUserId(id, NumberUtils.toByte(request.getParameter("type")));
    }

    @RequiresPermissions("Sys:permission:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap modelMap) {
        SysSystemExample sysSystemExample = new SysSystemExample();
        sysSystemExample.createCriteria()
                .andStatusEqualTo((byte) 1);
        List<SysSystem> SysSystems = sysSystemService.selectByExample(sysSystemExample);
        modelMap.put("SysSystems", SysSystems);
        return "/manage/permission/create";
    }

    @RequiresPermissions("Sys:permission:create")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(SysPermission sysPermission) {
        ComplexResult result = FluentValidator.checkAll()
                .on(sysPermission.getName(), new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new Result(ResultConstant.INVALID_LENGTH, result.getErrors());
        }
        long time = System.currentTimeMillis();
        sysPermission.setCtime(time);
        sysPermission.setOrders(time);
        int count = sysPermissionService.insertSelective(sysPermission);
        return new Result(ResultConstant.SUCCESS, count);
    }

    @RequiresPermissions("Sys:permission:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = sysPermissionService.deleteByPrimaryKeys(ids);
        return new Result(ResultConstant.SUCCESS, count);
    }

    @RequiresPermissions("Sys:permission:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        SysSystemExample SysSystemExample = new SysSystemExample();
        SysSystemExample.createCriteria()
                .andStatusEqualTo((byte) 1);
        List<SysSystem> SysSystems = sysSystemService.selectByExample(SysSystemExample);
        SysPermission permission = sysPermissionService.selectByPrimaryKey(id);
        modelMap.put("permission", permission);
        modelMap.put("SysSystems", SysSystems);
        return "/manage/permission/update";
    }

    @RequiresPermissions("Sys:permission:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") int id, SysPermission sysPermission) {
        ComplexResult result = FluentValidator.checkAll()
                .on(sysPermission.getName(), new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new Result(ResultConstant.INVALID_LENGTH, result.getErrors());
        }
        sysPermission.setPermissionId(id);
        int count = sysPermissionService.updateByPrimaryKeySelective(sysPermission);
        return new Result(ResultConstant.SUCCESS, count);
    }

}
