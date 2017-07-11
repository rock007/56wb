package com.fp.gan.system.dao.sys.service;

import com.alibaba.fastjson.JSONArray;
import com.fp.gan.system.comm.base.BaseService;
import com.fp.gan.system.dao.sys.model.SysUserPermission;
import com.fp.gan.system.dao.sys.model.SysUserPermissionExample;

/**
* SysUserPermissionService接口
*/
public interface SysUserPermissionService extends BaseService<SysUserPermission, SysUserPermissionExample> {

    /**
     * 用户权限
     * @param datas 权限数据
     * @param id 用户id
     * @return
     */
    int permission(JSONArray datas, int id);

}