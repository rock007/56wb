package com.fp.gan.system.dao.sys.service;

import com.alibaba.fastjson.JSONArray;
import com.fp.gan.system.comm.base.BaseService;
import com.fp.gan.system.dao.sys.model.SysRolePermission;
import com.fp.gan.system.dao.sys.model.SysRolePermissionExample;

/**
* SysRolePermissionService接口
*/
public interface SysRolePermissionService extends BaseService<SysRolePermission, SysRolePermissionExample> {

    /**
     * 角色权限
     * @param datas 权限数据
     * @param id 角色id
     * @return
     */
    int rolePermission(JSONArray datas, int id);

}