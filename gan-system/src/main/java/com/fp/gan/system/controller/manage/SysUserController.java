package com.fp.gan.system.controller.manage;

import com.alibaba.fastjson.JSONArray;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.fp.gan.system.comm.constant.Result;
import com.fp.gan.system.comm.constant.ResultConstant;
import com.fp.gan.system.controller.BaseController;
import com.fp.gan.system.dao.sys.model.*;
import com.fp.gan.system.dao.sys.service.*;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.fp.gan.system.comm.validator.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 用户controller
 * sam is here  2017/2/6.
 */
@Controller
@RequestMapping("/manage/user")
public class SysUserController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysOrganizationService sysOrganizationService;

    @Autowired
    private SysUserOrganizationService sysUserOrganizationService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysUserPermissionService sysUserPermissionService;

    @RequiresPermissions("Sys:user:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/user/index";
    }


    @RequiresPermissions("Sys:user:organization")
    @RequestMapping(value = "/organization/{id}", method = RequestMethod.GET)
    public String organization(@PathVariable("id") int id, ModelMap modelMap) {
        // 所有组织
        List<SysOrganization> sysOrganizations = sysOrganizationService.selectByExample(new SysOrganizationExample());
        // 用户拥有组织
        SysUserOrganizationExample sysUserOrganizationExample = new SysUserOrganizationExample();
        sysUserOrganizationExample.createCriteria()
                .andUserIdEqualTo(id);
        List<SysUserOrganization> sysUserOrganizations = sysUserOrganizationService.selectByExample(sysUserOrganizationExample);
        modelMap.put("SysOrganizations", sysOrganizations);
        modelMap.put("SysUserOrganizations", sysUserOrganizations);
        return "/manage/user/organization";
    }


    @RequiresPermissions("Sys:user:organization")
    @RequestMapping(value = "/organization/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object organization(@PathVariable("id") int id, HttpServletRequest request) {
        String[] organizationIds = request.getParameterValues("organizationId");
        sysUserOrganizationService.organization(organizationIds, id);
        return new Result(ResultConstant.SUCCESS, "");
    }


    @RequiresPermissions("Sys:user:role")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public String role(@PathVariable("id") int id, ModelMap modelMap) {
        // 所有角色
        List<SysRole> SysRoles = sysRoleService.selectByExample(new SysRoleExample());
        // 用户拥有角色
        SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
        sysUserRoleExample.createCriteria()
                .andUserIdEqualTo(id);
        List<SysUserRole> SysUserRoles = sysUserRoleService.selectByExample(sysUserRoleExample);
        modelMap.put("SysRoles", SysRoles);
        modelMap.put("SysUserRoles", SysUserRoles);
        return "/manage/user/role";
    }


    @RequiresPermissions("Sys:user:role")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object role(@PathVariable("id") int id, HttpServletRequest request) {
        String[] roleIds = request.getParameterValues("roleId");
        sysUserRoleService.role(roleIds, id);
        return new Result(ResultConstant.SUCCESS, "");
    }


    @RequiresPermissions("Sys:user:permission")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.GET)
    public String permission(@PathVariable("id") int id, ModelMap modelMap) {
        SysUser user = sysUserService.selectByPrimaryKey(id);
        modelMap.put("user", user);
        return "/manage/user/permission";
    }


    @RequiresPermissions("Sys:user:permission")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object permission(@PathVariable("id") int id, HttpServletRequest request) {
        JSONArray datas = JSONArray.parseArray(request.getParameter("datas"));
        sysUserPermissionService.permission(datas, id);
        return new Result(ResultConstant.SUCCESS, datas.size());
    }


    @RequiresPermissions("Sys:user:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        SysUserExample sysUserExample = new SysUserExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            sysUserExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            sysUserExample.or()
                    .andRealnameLike("%" + search + "%");
            sysUserExample.or()
                    .andUsernameLike("%" + search + "%");
        }
        List<SysUser> rows = sysUserService.selectByExampleForOffsetPage(sysUserExample, offset, limit);
        long total = sysUserService.countByExample(sysUserExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }


    @RequiresPermissions("Sys:user:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/user/create";
    }


    @RequiresPermissions("Sys:user:create")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(SysUser sysUser) {
        ComplexResult result = FluentValidator.checkAll()
                .on(sysUser.getUsername(), new LengthValidator(1, 20, "帐号"))
                .on(sysUser.getPassword(), new LengthValidator(5, 32, "密码"))
                .on(sysUser.getRealname(), new NotNullValidator("姓名"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new Result(ResultConstant.INVALID_LENGTH, result.getErrors());
        }
        long time = System.currentTimeMillis();
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        sysUser.setSalt(salt);
        //sysUser.setPassword(MD5Util.MD5(sysUser.getPassword() + sysUser.getSalt()));
        sysUser.setPassword(sysUser.getPassword());
        sysUser.setCtime(time);
        sysUser = sysUserService.createUser(sysUser);
        if (null == sysUser) {
            return new Result(ResultConstant.FAILED, "帐号名已存在！");
        }
        _log.info("新增用户，主键：userId={}", sysUser.getUserId());
        return new Result(ResultConstant.SUCCESS, 1);
    }


    @RequiresPermissions("Sys:user:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = sysUserService.deleteByPrimaryKeys(ids);
        return new Result(ResultConstant.SUCCESS, count);
    }


    @RequiresPermissions("Sys:user:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        SysUser user = sysUserService.selectByPrimaryKey(id);
        modelMap.put("user", user);
        return "/manage/user/update";
    }


    @RequiresPermissions("Sys:user:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") int id, SysUser sysUser) {
        ComplexResult result = FluentValidator.checkAll()
                .on(sysUser.getUsername(), new LengthValidator(1, 20, "帐号"))
                .on(sysUser.getRealname(), new NotNullValidator("姓名"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new Result(ResultConstant.INVALID_LENGTH, result.getErrors());
        }
        // 不允许直接改密码
        sysUser.setPassword(null);
        sysUser.setUserId(id);
        int count = sysUserService.updateByPrimaryKeySelective(sysUser);
        return new Result(ResultConstant.SUCCESS, count);
    }

}
