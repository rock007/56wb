package com.fp.gan.system.dao.sys.service;

import com.alibaba.fastjson.JSONArray;
import com.fp.gan.system.comm.base.BaseService;
import com.fp.gan.system.dao.sys.model.SysPermission;
import com.fp.gan.system.dao.sys.model.SysPermissionExample;

/**
* SysPermissionService接口
* sam is here  2017/3/20.
*/
public interface SysPermissionService extends BaseService<SysPermission, SysPermissionExample> {

    JSONArray getTreeByRoleId(Integer roleId);

    JSONArray getTreeByUserId(Integer usereId, Byte type);

}